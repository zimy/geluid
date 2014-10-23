package me.zimy.geluid.informatories;

import java.util.Set;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremin
 */
public interface ServerInformatory {
    AudioFileMetadata getMetadata(String fileName);

    Set<String> getAvailableExtensions();
}
