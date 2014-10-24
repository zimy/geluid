package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Author;
import me.zimy.geluid.repositories.AlbumRepository;
import me.zimy.geluid.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private AlbumRepository albumRepository;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Author> getAll() {
        return repository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getOneByURIRequest(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Author getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return repository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Author post(@RequestParam String name) {
        Author author = new Author();
        author.setName(name);
        return repository.saveAndFlush(author);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        repository.delete(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
    public Author getByAlbum(@PathVariable Long id) {
        return (albumRepository.findOne(id)).getAuthor();
    }
}
