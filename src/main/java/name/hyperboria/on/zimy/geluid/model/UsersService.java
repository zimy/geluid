package name.hyperboria.on.zimy.geluid.model;

import name.hyperboria.on.zimy.geluid.dao.userDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO template javadoc
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 26.07.14
 */
public class UsersService implements UserDetailsService {
    private userDao dao;
    private Log log = LogFactory.getLog(UsersService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByName(username);
        log.info("User " + username + ((user == null) ? " not " : " " + "found"));
        Set<GrantedAuthority> setRoles = new HashSet<>();
        for (UserRole userRole : user.getUserRole()) {
            setRoles.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, new ArrayList<>(setRoles));
    }

    public void setDao(userDao dao) {
        this.dao = dao;
    }
}
