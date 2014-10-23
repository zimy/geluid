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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdSuperclass that = (IdSuperclass) o;
        return id == that.id && !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IdSuperclass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
