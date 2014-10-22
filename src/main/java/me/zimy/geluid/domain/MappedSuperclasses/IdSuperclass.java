package me.zimy.geluid.domain.MappedSuperclasses;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@SuppressWarnings("UnusedDeclaration")
@MappedSuperclass
public class IdSuperclass implements Serializable {
    @Id
    @GeneratedValue
    long id;
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
