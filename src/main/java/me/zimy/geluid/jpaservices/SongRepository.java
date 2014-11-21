package me.zimy.geluid.jpaservices;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByName(String name);

    List<Song> findByGenre(Genre genre);

    List<Song> findByAuthor(Author author);

    List<Song> findByAlbum(Album album);
}
