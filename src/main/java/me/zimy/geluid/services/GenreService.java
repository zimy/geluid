package me.zimy.geluid.services;

import me.zimy.geluid.domain.Genre;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 11/21/14.
 */
public interface GenreService {
    Genre findOne(Long id);

    Genre findByName(String name);

    List<Genre> getAll();

    Genre save(Genre genre);

    void delete(Long id);
}
