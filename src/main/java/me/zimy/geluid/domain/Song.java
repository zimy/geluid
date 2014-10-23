package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Song extends IdSuperclass {
    long length;
    @Lob
    @JsonIgnore
    @Column(length = 65535)
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
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

    @JsonProperty
    public String getAlbumValue() {
        return album.getName();
    }

    @JsonProperty
    public String getAuthorValue() {
        return author.getName();
    }

    @JsonProperty
    public String getGenreValue() {
        return genre.getName();
    }

    @Override
    public String toString() {
        return "{" +
                "length: " + length +
                ", filename: \"" + filename +
                "\", album: \"" + (album == null ? "" : album.getName()) +
                "\", author: \"" + (author == null ? "" : author.getName()) +
                "\", genre: \"" + (genre == null ? "" : genre.getName()) +
                "\", name: \"" + getName() +
                "\", id: " + getId() + '}';
    }
}
