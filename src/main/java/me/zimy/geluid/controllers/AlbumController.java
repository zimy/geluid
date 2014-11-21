package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.services.AlbumService;
import me.zimy.geluid.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AuthorService authorService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Album> getAll() {
        return albumService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Album getOneByURIRequest(@PathVariable Long id) {
        return albumService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name" })
    public Album getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return albumService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Album post(@RequestParam String name) {
        Album album = new Album();
        album.setName(name);
        return albumService.save(album);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        albumService.delete(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public List<Album> byAuthor(@PathVariable Long id) {
        return albumService.findByAuthor(authorService.findOne(id));
    }
}
