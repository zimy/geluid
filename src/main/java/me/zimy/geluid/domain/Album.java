package me.zimy.geluid.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    @JsonManagedReference
    Set<Song> songs;
    @ManyToOne
    @JsonBackReference
    Author author;

    @JsonProperty
    public Long getSongsValue() {
        return songs == null ? 0L : songs.size();
    }

    @JsonProperty
    public String getAuthorValue() {
        return author.getName();
    }

    public void setAuthor(Author author) {
        this.author = author;
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
