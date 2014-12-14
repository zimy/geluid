package me.zimy.geluid.scanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Component
public class StartupScanning {
    @Autowired
    GeluidScanner geluidScanner;

    @PostConstruct
    public void setGeluidScanner() {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
        geluidScanner.setDepth(10);
        geluidScanner.run();
    }
}
