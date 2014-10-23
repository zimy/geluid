package me.zimy.geluid.player;

import me.zimy.geluid.domain.Song;

import java.util.List;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremin
 */
public interface ServerPlayerInterface {
    void play();

    void pause();

    void setPlayList(List<Song> playList);

    void next();

    void previous();

    Song current();

    void resume();

    void stop();

    void setCurrent(int title);
}