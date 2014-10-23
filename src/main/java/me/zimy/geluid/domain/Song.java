package me.zimy.geluid.domain;

import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Song extends IdSuperclass {
    long length;
    String filename;
    @ManyToOne
    Album album;
    @ManyToOne
    Author author;
    @ManyToOne
    Genre genre;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Song song = (Song) o;
        return length == song.length && !(album != null ? !album.equals(song.album) : song.album != null) && !(filename != null ? !filename.equals(song.filename) : song.filename != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        return result;
    }
}
