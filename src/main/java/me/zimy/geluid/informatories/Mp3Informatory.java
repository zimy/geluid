package me.zimy.geluid.informatories;

import com.mpatric.mp3agic.Mp3File;
import me.zimy.geluid.scanning.ExtensionFinder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremein
 */
@Component
public class Mp3Informatory implements ServerInformatory {

    Set<String> ext = new HashSet<>();

    {
        ext.add("mp3");
    }

    @Override
    public AudioFileMetadata getMetadata(String fileName) {
        AudioFileMetadata result = null;
        if (ext.contains(ExtensionFinder.getExtension(fileName))) {
            try {
                Mp3File mpf = new Mp3File(fileName);
                if (mpf.getId3v1Tag() != null) {
                    result = new AudioFileMetadata(mpf.getId3v1Tag().getTitle(),
                            mpf.getLengthInSeconds(), mpf.getId3v1Tag().getArtist(),
                            mpf.getId3v1Tag().getGenreDescription(), mpf.getId3v1Tag().getAlbum());
                }
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
