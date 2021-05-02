package monika.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import monika.projectmanagement.dto.ChartData;
import monika.projectmanagement.entity.Book;
import monika.projectmanagement.repository.AuthorRepo;
import monika.projectmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepo authorRepo;

    @GetMapping("/")
    public String displayHome(Model model, String keyword) {

        List<Book> books = bookRepository.findAll();

        if (keyword != null){
            model.addAttribute("books", bookRepository.findByKeyword(keyword));
        } else {
            model.addAttribute("books", books);
        }

        return "home/home";
    }

//    @GetMapping("/booksFiltered")
//    public String displayBooksFiltered(Model model, String keyword) {
//
//        List<Book> books = bookRepository.findAll();
//
//        model.addAttribute("books", books);
//
//
//        return "books/books-filter";
//    }

    @GetMapping("/stage")
    public String displayStage(Model model) throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();
        List<ChartData> bookData = bookRepository.getBookStage();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(bookData);

        model.addAttribute("projectStageCnt", jsonString);
        return "stage/stage";
    }

    public List<Book> findByKeyword(String keyword){
        return bookRepository.findByKeyword(keyword);
    }

}
