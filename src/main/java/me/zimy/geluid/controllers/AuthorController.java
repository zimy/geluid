package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Author;
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
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AlbumService albumService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getOneByURIRequest(@PathVariable Long id) {
        return authorService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public Author getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return authorService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Author post(@RequestParam String name) {
        Author author = new Author();
        author.setName(name);
        return authorService.save(author);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        authorService.delete(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
    public Author getByAlbum(@PathVariable Long id) {
        return (albumService.findOne(id)).getAuthor();
    }
}
