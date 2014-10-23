package me.zimy.geluid.repositories;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByName(String name);

    List<Album> findByAuthor(Author author);
}
