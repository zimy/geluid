package name.hyperboria.on.zimy.geluid.dao;

import name.hyperboria.on.zimy.geluid.model.User;

import java.util.List;

/**
 * TODO template javadoc
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 15.07.14
 */
public interface userDao {
    public List<User> findAll();
    public User findById(Integer id);
    public User findByName(String username);
    public User save(User user);
    public void delete(User user);
}
