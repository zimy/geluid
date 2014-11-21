package me.zimy.geluid.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zimy on 11/20/14.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "music")
public class MusicLocationList {
    List<String> location = new ArrayList<>();

    public List<String> getLocation() {
        return location;
    }
}
