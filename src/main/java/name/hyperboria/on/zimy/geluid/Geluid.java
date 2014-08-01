package name.hyperboria.on.zimy.geluid;

import name.hyperboria.on.zimy.geluid.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * TODO template javadoc
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 15.07.14
 */
@ComponentScan
@EnableAutoConfiguration
@EnableWebSecurity
@EnableTransactionManagement
@ImportResource({"classpath:application-context.xml","classpath:spring-security.xml"})
@Transactional
public class Geluid {

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    UserDetailsService userDetailsService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Geluid.class, args);
//        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
    }
}