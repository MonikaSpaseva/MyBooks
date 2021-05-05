package monika.projectmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StorageService {


    public String saveFile(MultipartFile file) throws IOException {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
        String fileName = date + file.getOriginalFilename();

        // folderPath here is /sismed/temp/exames
        String folderPath = "C:/data";
        String filePath = folderPath + "/" + fileName;

        // Copies Spring's multipartfile inputStream to /sismed/temp/exames (absolute path)
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public byte[] readFile(String fileName) throws IOException {
        File file = new File("C:/data/" + fileName);
        InputStream is = new FileInputStream(file);
        return is.readAllBytes();
    }
}
