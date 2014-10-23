package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Album extends IdSuperclass {
    @OneToMany
    @JsonManagedReference
    Set<Song> songs;
    @ManyToOne
    @JsonBackReference
    Author author;

    public Set<Song> getSongs() {
        return songs;
    }

    public String getAuthorValue() {
        return author.getName();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return !(author != null ? !author.equals(album.author) : album.author != null);
    }

    @Override
    public String toString() {
        return "{" +
                "songs: " + (songs == null ? 0 : songs.size()) +
                ", author: \"" + (author == null ? "" : author.getName()) +
                "\", id: " + getId() +
                ", name: \"" + getName() + "\"}";
    }
}
