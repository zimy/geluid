package me.zimy.geluid.scanning;

import me.zimy.geluid.informatories.AudioFileMetadata;
import me.zimy.geluid.informatories.ServerInformatory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Service
public class JpaRepositoryMetadataSaver implements AudioSaver {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JpaRepositoryMetadataSaver.class);

    @Override
    public void persistAudio(Path file, ServerInformatory informatory1) {
        AudioFileMetadata metadata = informatory1.getMetadata(file.toString());
        logger.info("Saving something");

    }
}
