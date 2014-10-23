package me.zimy.geluid.scanning;

/**
 * @author Dmitriy &lt;Shnay&gt; Eremin
 */
public class ExtensionFinder {
    public static String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
