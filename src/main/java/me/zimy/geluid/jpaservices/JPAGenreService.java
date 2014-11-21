package me.zimy.geluid.jpaservices;

import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 11/21/14.
 */
@Service
@Repository
public class JPAGenreService implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre findOne(Long id) {
        return genreRepository.getOne(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long id) {
        genreRepository.delete(id);
    }
}
