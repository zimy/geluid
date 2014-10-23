package me.zimy.geluid.player;

import me.zimy.geluid.domain.Song;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremin
 */
@Component
public class ServerPlayer implements ServerPlayerInterface {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    private final Object playerLock = new Object();
    List<Song> playList = new ArrayList<>();
    int current;
    private PlayerStatus playerStatus = PlayerStatus.INITIAL;

    @Override
    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case INITIAL:
                    threadPoolExecutor.execute(new InnerOperation());
                    playerStatus = PlayerStatus.PLAYING;
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    void playInternal() {
        while (playerStatus != PlayerStatus.FINISHED) {
            final File file = new File(playList.get(current).getFilename());
            try (final AudioInputStream in = getAudioInputStream(file)) {
                final AudioFormat outFormat = getOutFormat(in.getFormat());
                final Info info = new Info(SourceDataLine.class, outFormat);
                try (final SourceDataLine line = (SourceDataLine) AudioSystem
                        .getLine(info)) {
                    if (line != null) {
                        line.open(outFormat);
                        line.start();
                        stream(getAudioInputStream(outFormat, in), line);
                        line.drain();
                        line.stop();
                    }
                }
            } catch (UnsupportedAudioFileException | LineUnavailableException
                    | IOException e) {
                throw new IllegalStateException(e);
            }
            synchronized (playerLock) {
                while (playerStatus == PlayerStatus.PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        break;
                    }
                }
            }
        }
        synchronized (playerLock) {
            playerStatus = PlayerStatus.FINISHED;
        }
    }

    @Override
    public void pause() {
        synchronized (playerLock) {
            if (playerStatus == PlayerStatus.PLAYING) {
                playerStatus = PlayerStatus.PAUSED;
            }
        }
    }

    @Override
    public void resume() {
        synchronized (playerLock) {
            if (playerStatus == PlayerStatus.PAUSED) {
                playerStatus = PlayerStatus.PLAYING;
                playerLock.notifyAll();
            }
        }
    }

    @Override
    public void stop() {
        synchronized (playerLock) {
            playerStatus = PlayerStatus.STOPPED;
            playerLock.notifyAll();
        }
    }

    @Override
    public void setCurrent(int title) {
        current = title;
        stop();
        waitPlease();
        play();
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private boolean stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[65536];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            while (playerStatus == PlayerStatus.PAUSED) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (playerStatus == PlayerStatus.STOPPED) {
                return
                        false;
            }

            line.write(buffer, 0, n);
        }
        return true;
    }

    @Override
    public void setPlayList(List<Song> playList) {
        this.playList.clear();
        this.playList.addAll(playList);
        current = 0;
    }

    @Override
    public void next() {
        current++;
        current %= playList.size();
        stop();
        waitPlease();
        play();
    }

    @Override
    public void previous() {
        current--;
        current %= playList.size();
        stop();
        waitPlease();
        play();
    }

    @Override
    public Song current() {
        if (current != -1)
            return playList.get(current);
        return null;
    }

    void waitPlease() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private enum PlayerStatus {
        INITIAL,
        PLAYING,
        PAUSED,
        FINISHED,
        STOPPED
    }

    private class InnerOperation implements Runnable {
        @Override
        public void run() {
            while (true) {
                playInternal();
                next();
            }
        }
    }
}