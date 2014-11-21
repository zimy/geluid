package me.zimy.geluid.services;

import me.zimy.geluid.domain.Author;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 11/21/14.
 */
public interface AuthorService {
    Author findOne(Long id);

    List<Author> getAll();

    Author save(Author author);

    void delete(Long id);

    List<Author> findByName(String name);
}
