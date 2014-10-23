package me.zimy.geluid.scanning;

import me.zimy.geluid.informatories.ServerInformatory;

import java.nio.file.Path;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
public interface AudioSaver {
    void persistAudio(Path file, ServerInformatory informatory1);
}
