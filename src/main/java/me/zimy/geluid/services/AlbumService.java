package me.zimy.geluid.services;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 11/21/14.
 */
public interface AlbumService {
    List<Album> getAll();

    Album findOne(Long id);

    Album save(Album album);

    void delete(Long id);

    List<Album> findByAuthor(Author one);
}
