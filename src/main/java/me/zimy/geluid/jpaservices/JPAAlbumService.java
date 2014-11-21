package me.zimy.geluid.jpaservices;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.services.AlbumService;
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
public class JPAAlbumService implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findOne(Long id) {
        return albumRepository.getOne(id);
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public void delete(Long id) {
        albumRepository.delete(id);
    }

    @Override
    public List<Album> findByAuthor(Author one) {
        return albumRepository.findByAuthor(one);
    }
}
