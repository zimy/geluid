package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Song;
import me.zimy.geluid.services.AlbumService;
import me.zimy.geluid.services.AuthorService;
import me.zimy.geluid.services.GenreService;
import me.zimy.geluid.services.SongService;
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
    private SongService songService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AlbumService albumService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Song> getAll() {
        return songService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Song getOneByURIRequest(@PathVariable Long id) {
        return songService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = {"name" })
    public Song getOneByRequestParam(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return songService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeOne(@PathVariable Long id) {
        songService.delete(id);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
    public List<Song> getByAlbum(@PathVariable Long id) {
        return songService.findByAlbum(songService.findOne(id).getAlbum());
    }

    @ResponseBody
    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public List<Song> getByAuthor(@PathVariable Long id) {
        return songService.findByAuthor(songService.findOne(id).getAuthor());
    }


    @ResponseBody
    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public List<Song> getByGenre(@PathVariable Long id) {
        return songService.findByGenre(songService.findOne(id).getGenre());
    }

    @ResponseBody
    @RequestMapping(value = "/author/all/{id}", method = RequestMethod.GET)
    public List<Song> getAuthorAll(@PathVariable Long id) {
        return songService.findByAuthor(authorService.findOne(id));
    }

    @ResponseBody
    @RequestMapping(value = "/genre/all/{id}", method = RequestMethod.GET)
    public List<Song> getGenreAll(@PathVariable Long id) {
        return songService.findByGenre(genreService.findOne(id));
    }

    @ResponseBody
    @RequestMapping(value = "/album/all/{id}", method = RequestMethod.GET)
    public List<Song> getAlbumAll(@PathVariable Long id) {
        return songService.findByAlbum(albumService.findOne(id));
    }
}
