package me.zimy.geluid.repositories;

import me.zimy.geluid.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}
