package me.zimy.geluid.services;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 11/21/14.
 */
public interface SongService {
    List<Song> getAll();

    Song findOne(Long id);

    void delete(Long id);

    List<Song> findByAlbum(Album album);

    List<Song> findByAuthor(Author author);

    List<Song> findByGenre(Genre genre);

    Song save(Song song);
}
