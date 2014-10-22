package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreRepository repository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Genre getOneByURIRequest(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Genre getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Genre post(@RequestParam String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return repository.saveAndFlush(genre);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Genre removeOne(@PathVariable Long id) {
        Genre genre = repository.findOne(id);
        repository.delete(id);
        return genre;
    }
}
