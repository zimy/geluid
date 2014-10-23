package me.zimy.geluid.controllers;

import me.zimy.geluid.scanning.GeluidScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/scan")
public class ScanController {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    @Autowired
    GeluidScanner geluidScanner;

    @ResponseBody
    @RequestMapping(value = "/{depth}", method = RequestMethod.GET)
    public String getAll(@PathVariable final Integer depth) {
        geluidScanner.setDepth(depth);
        threadPoolExecutor.execute(geluidScanner);
        return "{\"message\":\"OK\"}";
    }
}
