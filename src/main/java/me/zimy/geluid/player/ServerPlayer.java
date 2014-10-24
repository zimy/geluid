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

@Component
public class ServerPlayer implements ServerPlayerInterface {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    private final Object playerLock = new Object();
    List<Song> playList = new ArrayList<>();
    int current;
    private State playerStatus = State.INITIAL;
    final Runnable r = new Runnable() {
        public void run() {
            while (playerStatus != State.STOPPED) {
                playInternal();
                next();
            }
        }
    };

    @Override
    public void play() {
        if (playerStatus == State.INITIAL || playerStatus == State.STOPPED || playerStatus == State.PAUSED) {
            synchronized (playerLock) {
                switch (playerStatus) {
                    case INITIAL:
                    case STOPPED:
                        threadPoolExecutor.execute(r);
                        playerStatus = State.PLAYING;
                        break;
                    case PAUSED:
                        resume();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    void playInternal() {
        while (playerStatus != State.FINISHED) {
            final File file = new File(playList.get(current).getFilename());
            try (final AudioInputStream in = getAudioInputStream(file)) {
                AudioFormat inFormat = in.getFormat();
                final int ch = inFormat.getChannels();
                final float rate = inFormat.getSampleRate();
                final AudioFormat outFormat = new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
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
                while (playerStatus == State.PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        break;
                    }
                }
            }
        }
        synchronized (playerLock) {
            playerStatus = State.FINISHED;
        }
    }

    @Override
    public void pause() {
        synchronized (playerLock) {
            if (playerStatus == State.PLAYING) {
                playerStatus = State.PAUSED;
            }
        }
    }

    @Override
    public void resume() {
        synchronized (playerLock) {
            if (playerStatus == State.PAUSED) {
                playerStatus = State.PLAYING;
                playerLock.notifyAll();
            }
        }
    }

    @Override
    public void stop() {
        if (playerStatus != State.STOPPED) {
            synchronized (playerLock) {
                if (playerStatus != State.STOPPED) {
                    playerStatus = State.STOPPED;
                    playerLock.notifyAll();
                }
            }
        }
    }

    @Override
    public void setCurrent(int title) {
        current = title;
        stopAndStart();
    }

    private void stopAndStart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stop();
                    Thread.sleep(2000);
                    play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[65536];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            while (playerStatus == State.PAUSED) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (playerStatus == State.STOPPED) {
                return false;
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
        stopAndStart();
    }

    @Override
    public void previous() {
        current--;
        current += playList.size();
        current %= playList.size();
        stopAndStart();
    }

    @Override
    public Song current() {
        return current != -1 ? playList.get(current) : null;
    }

    enum State {
        INITIAL,
        PLAYING,
        PAUSED,
        FINISHED,
        STOPPED
    }
}