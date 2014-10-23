package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/albums")
public class AlbomController {
    @Autowired
    private AlbumRepository repository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Album> getAll() {
        return repository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Album getOneByURIRequest(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Album getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Album post(@RequestParam String name) {
        Album album = new Album();
        album.setName(name);
        return repository.saveAndFlush(album);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        repository.delete(id);
        return "OK";
    }
}
