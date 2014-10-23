package me.zimy.geluid.informatories;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremein
 */
public class AudioFileMetadata {
    String path;
    String title;
    long lengthInSeconds;
    String author;
    String genre;
    String album;

    public AudioFileMetadata(String title, long lengthInSeconds, String author,
                             String genre, String album, String path) {
        this.album = album;
        this.author = author;
        this.genre = genre;
        this.lengthInSeconds = lengthInSeconds;
        this.title = title;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public long getLengthInSeconds() {
        return lengthInSeconds;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }
}
