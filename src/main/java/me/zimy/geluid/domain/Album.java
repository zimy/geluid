package me.zimy.geluid.domain;

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
    Set<Song> songs;
    @ManyToOne
    Author author;

    public Set<Song> getSongs() {
        return songs;
    }

    public Author getAuthor() {
        return author;
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
}
