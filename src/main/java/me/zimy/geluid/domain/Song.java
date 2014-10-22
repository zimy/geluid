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
    String hash;
    @ManyToOne
    Album album;
    @ManyToOne
    Author author;
    @ManyToOne
    Genre genre;
}
