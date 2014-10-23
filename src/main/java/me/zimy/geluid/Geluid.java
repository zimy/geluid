package me.zimy.geluid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@EntityScan
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Geluid {
    public static void main(String[] args) {
        SpringApplication.run(Geluid.class, args);
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
    }
}
