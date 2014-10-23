package me.zimy.geluid.informatories;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremein
 */
@Component
public class SampleOggInformatory implements ServerInformatory {
    @Override
    public AudioFileMetadata getMetadata(String fileName) {
        return null;
    }

    @Override
    public Set<String> getAvailableExtensions() {
        Set<String> extensions = new HashSet<>();
        extensions.add("ogg");
        return extensions;
    }
}
