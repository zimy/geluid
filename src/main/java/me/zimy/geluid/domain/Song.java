package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Song extends IdSuperclass {
    long length;
    @JsonIgnore
    String filename;
    @ManyToOne
    @JsonBackReference
    Album album;
    @ManyToOne
    @JsonBackReference
    Author author;
    @ManyToOne
    @JsonBackReference
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

    @Override
    public String toString() {
        return "Song{" +
                "length=" + length +
                ", filename='" + filename + '\'' +
                ", album=" + album +
                ", author=" + author +
                ", genre=" + genre +
                ", name=" + getName() +
                ", id=" + getId() +
                '}';
    }
}
