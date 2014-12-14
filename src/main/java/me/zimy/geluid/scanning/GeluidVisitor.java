package me.zimy.geluid.scanning;

import me.zimy.geluid.informatories.ServerInformatory;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class GeluidVisitor extends SimpleFileVisitor<Path> {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GeluidVisitor.class);

    Map<String, ServerInformatory> available;
    AudioSaver saver;

    public GeluidVisitor(Map<String, ServerInformatory> available, AudioSaver saver) {
        this.available = available;
        this.saver = saver;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        ServerInformatory informer = available.get(FilenameUtils.getExtension(file.toString()));
        if (attributes.isRegularFile() && informer != null) {
            saver.persistAudio(file, informer);
        }
        logger.debug("" + file.toString() + " visited");
        return FileVisitResult.CONTINUE;
    }
}