package me.zimy.geluid.scanning;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;
import me.zimy.geluid.informatories.AudioFileMetadata;
import me.zimy.geluid.informatories.ServerInformatory;
import me.zimy.geluid.repositories.AlbumRepository;
import me.zimy.geluid.repositories.AuthorRepository;
import me.zimy.geluid.repositories.GenreRepository;
import me.zimy.geluid.repositories.SongRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Service
@Transactional
public class JpaRepositoryMetadataSaver implements AudioSaver {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JpaRepositoryMetadataSaver.class);
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    SongRepository songRepository;

    @Override
    public void persistAudio(Path file, ServerInformatory informatory1) {
        AudioFileMetadata metadata = informatory1.getMetadata(file.toString());
        if (metadata != null) {
            logger.info("Supported content found at " + metadata.getPath());
            createOrFindSong(metadata);
        } else {
            logger.error("SHIT found at " + file.toString());
        }
    }

    public Genre createOrFindGenre(String name) {
        Genre genre = genreRepository.findByName(name);
        if (genre == null) {
            genre = new Genre();
            genre.setName(name);
            genre = genreRepository.saveAndFlush(genre);
            logger.info("Genre object created: " + genre);
        }
        return genre;
    }

    public Author createOrFindAuthor(String name) {
        Author author;
        List<Author> authors = authorRepository.findByName(name);
        if (authors == null || authors.size() == 0) {
            author = new Author();
            author.setName(name);
            author = authorRepository.saveAndFlush(author);
            logger.info("Author object created: " + author);
        } else {
            author = authors.get(0);
        }
        return author;
    }

    public Album createOrFindAlbum(String name, Author author) {
        Album album = null;
        List<Album> albumsByAuthor = albumRepository.findByAuthor(author);
        if (albumsByAuthor != null && albumsByAuthor.size() != 0) {
            for (Album album1 : albumsByAuthor) {
                if (album1.getName().equals(name)) {
                    album = album1;
                }
            }
        }
        if (album == null) {
            album = new Album();
            album.setName(name);
            album.setAuthor(author);
            album = albumRepository.saveAndFlush(album);
            author.getAlbums().add(album);
            logger.info("Album object created: " + album);
        }
        return album;
    }

    public Song createOrFindSong(AudioFileMetadata metadata) {
        Genre genre = createOrFindGenre(metadata.getGenre());
        Author author = createOrFindAuthor(metadata.getAuthor());
        Album album = createOrFindAlbum(metadata.getAlbum(), author);
        Song song = null;
        List<Song> getByAlbum = songRepository.findByAlbum(album);
        if (getByAlbum != null && getByAlbum.size() != 0) {
            for (Song song1 : getByAlbum) {
                if (metadata.getTitle().equals(song1.getName())) {
                    song = song1;
                }
            }
        }
        if (song == null) {
            song = new Song();
            song.setName(metadata.getTitle());
            song.setAuthor(author);
            song.setAlbum(album);
            song.setGenre(genre);
            song.setLength(metadata.getLengthInSeconds());
            song.setFilename(metadata.getPath());
            song = songRepository.saveAndFlush(song);
            genre.getSongs().add(song);
            album.getSongs().add(song);
            author.getSongs().add(song);
            logger.info("Song object created: " + song);
        }
        return song;
    }
}

