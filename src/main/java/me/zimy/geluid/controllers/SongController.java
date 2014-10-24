package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Song;
import me.zimy.geluid.repositories.AlbumRepository;
import me.zimy.geluid.repositories.AuthorRepository;
import me.zimy.geluid.repositories.GenreRepository;
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
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AlbumRepository albumRepository;

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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        repository.delete(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
    public List<Song> getByAlbum(@PathVariable Long id) {
        return repository.findByAlbum(repository.findOne(id).getAlbum());
    }

    @ResponseBody
    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public List<Song> getByAuthor(@PathVariable Long id) {
        return repository.findByAuthor(repository.findOne(id).getAuthor());
    }


    @ResponseBody
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public List<Song> getByGenre(@PathVariable Long id) {
        return repository.findByGenre(repository.findOne(id).getGenre());
    }

    @ResponseBody
    @RequestMapping(value = "/author/all/{id}", method = RequestMethod.GET)
    public List<Song> getAuthorAll(@PathVariable Long id) {
        return repository.findByAuthor(authorRepository.findOne(id));
    }

    @ResponseBody
    @RequestMapping(value = "/genre/all/{id}", method = RequestMethod.GET)
    public List<Song> getGenreAll(@PathVariable Long id) {
        return repository.findByGenre(genreRepository.findOne(id));
    }

    @ResponseBody
    @RequestMapping(value = "/album/all/{id}", method = RequestMethod.GET)
    public List<Song> getAlbumAll(@PathVariable Long id) {
        return repository.findByAlbum(albumRepository.findOne(id));
    }
}
