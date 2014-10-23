package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Song;
import me.zimy.geluid.player.ServerPlayerInterface;
import me.zimy.geluid.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    ServerPlayerInterface playerInterface;

    @Autowired
    SongRepository songRepository;

    @ResponseBody
    @RequestMapping(value = "/play/all", method = RequestMethod.GET)
    public String playAll() {
        playerInterface.setPlayList(songRepository.findAll());
        playerInterface.play();
        return "{\"message\":\"Started\"}";
    }

    @RequestMapping(value = "/play/list", method = RequestMethod.POST, consumes = "application/json")
    public String playlist(@RequestBody List<Song> songs) {
        playerInterface.stop();
        playerInterface.setPlayList(songs);
        playerInterface.play();
        return "{\"message\":\"CHANGED\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/play/{position}", method = RequestMethod.GET)
    public String playId(@PathVariable int position) {
        playerInterface.setCurrent(position);
        return "{\"message\":\"Changed\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public String play() {
        playerInterface.play();
        return "{\"message\":\"PLAY\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    public String pause() {
        playerInterface.pause();
        return "{\"message\":\"PAUSE\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public String resume() {
        playerInterface.resume();
        return "{\"message\":\"RESUME\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public String next() {
        playerInterface.next();
        return "{\"message\":\"NEXT\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/previous", method = RequestMethod.GET)
    public String previous() {
        playerInterface.previous();
        return "{\"message\":\"PREVIOUS\"}";
    }
}
