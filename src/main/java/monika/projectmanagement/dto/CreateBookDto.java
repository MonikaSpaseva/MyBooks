package monika.projectmanagement.dto;

import monika.projectmanagement.entity.Book;
import org.springframework.web.multipart.MultipartFile;


public class CreateBookDto extends Book {

    private MultipartFile file;



    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
