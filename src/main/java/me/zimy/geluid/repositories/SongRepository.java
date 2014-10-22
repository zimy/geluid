package me.zimy.geluid.repositories;

import me.zimy.geluid.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
