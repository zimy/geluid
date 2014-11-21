package me.zimy.geluid.informatories;

import me.zimy.geluid.scanning.ExtensionFinder;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremin
 */
@Component
public class Mp3Informatory implements ServerInformatory {

    Set<String> ext = new HashSet<>();

    {
        ext.add("mp3");
        ext.add("ogg");
        ext.add("flac");
    }

    @Override
    public AudioFileMetadata getMetadata(String fileName) {
        AudioFileMetadata result = null;
        if (!ext.contains(ExtensionFinder.getExtension(fileName))) {
            result = null;
        } else {
            try {
                AudioFile f = AudioFileIO.read(new File(fileName));
                if (f == null) {
                    return null;
                }
                Tag tag = f.getTag();
                if (tag == null) {
                    return null;
                }
                result = new AudioFileMetadata(tag.getFirst(FieldKey.TITLE),
                        f.getAudioHeader().getTrackLength(), tag.getFirst(FieldKey.ARTIST),
                        tag.getFirst(FieldKey.GENRE), tag.getFirst(FieldKey.ALBUM), fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @Override
    public Set<String> getAvailableExtensions() {
        return ext;
    }
}
