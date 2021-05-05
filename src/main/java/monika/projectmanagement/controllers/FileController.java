package monika.projectmanagement.controllers;

import monika.projectmanagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    StorageService storageService;

    @GetMapping(value = "/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] serveFile(@PathVariable String filename) throws IOException {

        return storageService.readFile(filename);
    }
}
