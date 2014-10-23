package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Song;
import me.zimy.geluid.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongRepository repository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Song> getAll() {
        return repository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Song getOneByURIRequest(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Song getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Song post(@RequestParam String name) {
        Song song = new Song();
        song.setName(name);
        return repository.saveAndFlush(song);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        repository.delete(id);
        return "OK";
    }
}
