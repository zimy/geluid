package me.zimy.geluid.controllers;

import me.zimy.geluid.player.ServerPlayerInterface;
import me.zimy.geluid.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/playall", method = RequestMethod.GET)
    public String playAll() {
        playerInterface.setPlayList(songRepository.findAll());
        playerInterface.play();
        return "Started";
    }

    @ResponseBody
    @RequestMapping(value = "/play/{position", method = RequestMethod.GET)
    public String playId(@PathVariable int position) {
        playerInterface.setCurrent(position);
        return "Changed";
    }

    @ResponseBody
    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public String play() {
        playerInterface.play();
        return "PLAY";
    }

    @ResponseBody
    @RequestMapping(value = "/pause", method = RequestMethod.GET)
    public String pause() {
        playerInterface.pause();
        return "PAUSE";
    }

    @ResponseBody
    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public String next() {
        playerInterface.next();
        return "NEXT";
    }

    @ResponseBody
    @RequestMapping(value = "/previous", method = RequestMethod.GET)
    public String previous() {
        playerInterface.previous();
        return "PREVIOUS";
    }
}
