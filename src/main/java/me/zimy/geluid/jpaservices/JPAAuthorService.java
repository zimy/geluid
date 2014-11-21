package me.zimy.geluid.jpaservices;

import me.zimy.geluid.domain.Author;
import me.zimy.geluid.services.AuthorService;
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
public class JPAAuthorService implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findOne(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}
