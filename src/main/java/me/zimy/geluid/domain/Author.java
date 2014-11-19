package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.zimy.geluid.domain.MappedSuperclasses.IdSuperclass;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Entity
public class Author extends IdSuperclass {
    @OneToMany
    @JsonIgnore
    Set<Album> albums = new HashSet<>();
    @OneToMany
    @JsonIgnore
    @JsonManagedReference
    Set<Song> songs = new HashSet<>();

    @JsonProperty
    public Long getAlbumsValue() {
        return albums == null ? 0L : albums.size();
    }

    @JsonProperty
    public Long getSongsValue() {
        return songs == null ? 0L : songs.size();
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "{" +
                "albums: " + (albums == null ? 0 : albums.size()) +
                ", songs: " + (songs == null ? 0 : songs.size()) +
                ", id: " + getId() +
                ", name: \"" + getName() + "\"}";
    }
}
