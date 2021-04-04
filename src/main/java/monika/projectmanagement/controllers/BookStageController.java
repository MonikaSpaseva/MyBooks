package monika.projectmanagement.controllers;

import monika.projectmanagement.entity.Book;
import monika.projectmanagement.entity.BookStatus;
import monika.projectmanagement.repository.BookRepository;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookstage")
public class BookStageController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/read")
    public String displayReadBooks(Model model){
        List<Book> books = bookRepository.findAll();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getStage().equals(BookStatus.ALREADY_READ)){
                filteredBooks.add(book);

            }
        }
      model.addAttribute("books", filteredBooks);
        return "books/list-read";
    }

    @GetMapping("/reading")
    public String displayCurrentlyReadingBooks(Model model){
        List<Book> books = bookRepository.findAll();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getStage().equals(BookStatus.CURRENTLY_READING)){
                filteredBooks.add(book);

            }
        }
        model.addAttribute("books", filteredBooks);
        return "books/list-currently-reading";
    }

    @GetMapping("/wanttoread")
    public String displayWantToReadBooks(Model model){
        List<Book> books = bookRepository.findAll();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getStage().equals(BookStatus.WANT_TO_READ)){
                filteredBooks.add(book);

            }
        }
        model.addAttribute("books", filteredBooks);

        return "books/list-want-to-read";
    }
}
