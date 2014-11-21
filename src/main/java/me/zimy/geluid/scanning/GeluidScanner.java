package me.zimy.geluid.scanning;

import me.zimy.geluid.configuration.MusicLocationList;
import me.zimy.geluid.informatories.ServerInformatory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Component
@ConfigurationProperties(prefix = "music")
public class GeluidScanner implements Runnable {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GeluidScanner.class);
    private final EnumSet<FileVisitOption> set = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
    @Autowired
    Collection<ServerInformatory> informatory;
    @Autowired
    AudioSaver saver;
    @Autowired
    MusicLocationList locationList;

    private int depth = 10;

    private static Map<String, ServerInformatory> getMapping(Collection<ServerInformatory> serverInformatory) {
        Map<String, ServerInformatory> available = new HashMap<>();
        for (ServerInformatory informatory : serverInformatory) {
            for (String extension : informatory.getAvailableExtensions()) {
                available.put(extension, informatory);
            }
        }
        return available;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public void run() {
        Map<String, ServerInformatory> available = getMapping(informatory);
        for (String s : locationList.getLocation()) {
            logger.info("File scanning started " + s);
            try {
                Files.walkFileTree(Paths.get(s).toAbsolutePath(), set, depth, new GeluidVisitor(available, saver));
            } catch (IOException e) {
                logger.info("File scanning failed:" + e.getMessage());
            }
            logger.info("File scanning ended in " + s);
        }
    }
}
