package me.zimy.geluid.jpaservices;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;
import me.zimy.geluid.services.SongService;
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
public class JPASongService implements SongService {

    @Autowired
    SongRepository songRepository;

    @Override
    public List<Song> getAll() {

        return songRepository.findAll();
    }

    @Override
    public Song findOne(Long id) {
        return songRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        songRepository.delete(id);
    }

    @Override
    public List<Song> findByAlbum(Album album) {
        return songRepository.findByAlbum(album);
    }

    @Override
    public List<Song> findByAuthor(Author author) {
        return songRepository.findByAuthor(author);
    }

    @Override
    public List<Song> findByGenre(Genre genre) {
        return songRepository.findByGenre(genre);
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }
}
