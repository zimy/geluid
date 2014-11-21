package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Genre;
import me.zimy.geluid.services.GenreService;
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
    private GenreService genreService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Genre getOneByURIRequest(@PathVariable Long id) {
        return genreService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Genre getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return genreService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Genre post(@RequestParam String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genreService.save(genre);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        genreService.delete(id);
        return "OK";
    }
}
