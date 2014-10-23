package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Album;
import me.zimy.geluid.domain.Author;
import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.domain.Song;
import me.zimy.geluid.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    @RequestMapping(method = RequestMethod.GET, params = {"name" })
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

    @ResponseBody
    @RequestMapping(value = "/album/{songid}", method = RequestMethod.GET)
    public Set<Song> getByAlbum(@PathVariable Long id) {
        Song that = repository.getOne(id);
        Album album = that.getAlbum();
        return album.getSongs();
    }

    @ResponseBody
    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public Set<Song> getByAuthor(@PathVariable Long id) {
        Song that = repository.getOne(id);
        Author author = that.getAuthor();
        return author.getSongs();
    }


    @ResponseBody
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public Set<Song> getByGenre(@PathVariable Long id) {
        Song that = repository.getOne(id);
        Genre author = that.getGenre();
        return author.getSongs();
    }

}
