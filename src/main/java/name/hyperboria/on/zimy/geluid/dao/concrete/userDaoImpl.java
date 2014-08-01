package name.hyperboria.on.zimy.geluid.dao.concrete;

import name.hyperboria.on.zimy.geluid.dao.userDao;
import name.hyperboria.on.zimy.geluid.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO template javadoc
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 15.07.14
 */
@Transactional
@Repository
public class userDaoImpl implements userDao {
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User findByName(String username) {
        List users = new ArrayList();
        users = getSessionFactory().getCurrentSession().createQuery("from User where username=?").setParameter(0, username).list();
        if (users.size()==0) {
            return null;
        }
        return (User) users.get(0);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
