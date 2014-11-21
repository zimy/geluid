package me.zimy.geluid.scanning;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;
import me.zimy.geluid.informatories.AudioFileMetadata;
import me.zimy.geluid.informatories.ServerInformatory;
import me.zimy.geluid.services.AlbumService;
import me.zimy.geluid.services.AuthorService;
import me.zimy.geluid.services.GenreService;
import me.zimy.geluid.services.SongService;
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
public class MetadataSaver implements AudioSaver {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MetadataSaver.class);
    @Autowired
    AlbumService albumService;
    @Autowired
    AuthorService authorService;
    @Autowired
    GenreService genreService;
    @Autowired
    SongService songService;

    @Override
    public void persistAudio(Path file, ServerInformatory informatory1) {
        AudioFileMetadata metadata = informatory1.getMetadata(file.toString());
        if (metadata != null) {
            logger.info("Reading " + metadata.getPath());
            createOrFindSong(metadata);
        } else {
            logger.warn("Unreadable: " + file.toString());
        }
    }

    public Genre createOrFindGenre(String name) {
        Genre genre = genreService.findByName(name);
        if (genre == null) {
            genre = new Genre();
            genre.setName(name);
            genre = genreService.save(genre);
            logger.info("Created new genre: " + genre);
        }
        return genre;
    }

    public Author createOrFindAuthor(String name) {
        Author author;
        List<Author> authors = authorService.findByName(name);
        if (authors == null || authors.size() == 0) {
            author = new Author();
            author.setName(name);
            author = authorService.save(author);
            logger.info("Created new author: " + author);
        } else {
            author = authors.get(0);
        }
        return author;
    }

    public Album createOrFindAlbum(String name, Author author) {
        Album album = null;
        List<Album> albumsByAuthor = albumService.findByAuthor(author);
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
            album = albumService.save(album);
            author.getAlbums().add(album);
            logger.info("Created new album: " + album);
        }
        return album;
    }

    public Song createOrFindSong(AudioFileMetadata metadata) {
        Genre genre = createOrFindGenre(metadata.getGenre());
        Author author = createOrFindAuthor(metadata.getAuthor());
        Album album = createOrFindAlbum(metadata.getAlbum(), author);
        Song song = null;
        List<Song> getByAlbum = songService.findByAlbum(album);
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
            song = songService.save(song);
            genre.getSongs().add(song);
            album.getSongs().add(song);
            author.getSongs().add(song);
            logger.info("Created new sound: " + song);
        }
        return song;
    }
}

