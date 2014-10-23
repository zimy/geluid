package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Author extends IdSuperclass {
    @OneToMany
    @JsonManagedReference
    Set<Album> albums;
    @OneToMany
    @JsonManagedReference
    Set<Song> songs;

    public Set<Album> getAlbums() {
        return albums;
    }

    public Set<Song> getSongs() {
        return songs;
    }
}
