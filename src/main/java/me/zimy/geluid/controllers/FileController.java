package me.zimy.geluid.controllers;

import me.zimy.geluid.domain.Song;
import me.zimy.geluid.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Dmitriy &lt;Zimy(x)&gt; Yakovlev
 */
@Controller
@RequestMapping("/tracks")
public class FileController {
    @Autowired
    SongRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws IOException {
        Song song = repository.getOne(id);
        Path path = Paths.get(song.getFilename());
        String mimeType = request.getServletContext().getMimeType(song.getFilename());
        response.setContentType(mimeType == null ? "application/octet-stream" : mimeType);
        try (OutputStream ostream = response.getOutputStream()) {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            if (attributes.isRegularFile()) {
                response.setContentLength((int) attributes.size());
                Files.copy(path, ostream);
            }
        }
    }
}
