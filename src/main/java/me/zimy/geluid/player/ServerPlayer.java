package me.zimy.geluid.player;

import me.zimy.geluid.domain.Song;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;

@Component
public class ServerPlayer implements ServerPlayerInterface, Runnable {

    private final static int NOTSTARTED = 0;
    private int playerStatus = NOTSTARTED;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private final static int STOPPED = 0;
    private final Object playerLock = new Object();
    List<Song> playList = new ArrayList<>();
    int current;

    @Override
    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {

                            while (true) {
                                playInternal();
                                next();
                            }
                        }
                    };
                    final Thread t = new Thread(r);
// t.setDaemon(true);
// t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
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

        while (playerStatus != FINISHED) {
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
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
// terminate player
                        break;
                    }
                }
            }
        }

        close();

    }

    @Override
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    @Override
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    @Override
    public void stop() {
        synchronized (playerLock) {
            playerStatus = STOPPED;
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

    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }

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

            while (playerStatus == PAUSED)
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            if (playerStatus == STOPPED)
                return
                        false;

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

    @Override
    public void run() {
        play();
    }


    void waitPlease() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}