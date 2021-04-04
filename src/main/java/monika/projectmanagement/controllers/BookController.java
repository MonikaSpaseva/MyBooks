package monika.projectmanagement.controllers;

import monika.projectmanagement.entity.Book;
import monika.projectmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping("/new")
    public String displayBookForm(Model model) {

        Book aBook = new Book();
        model.addAttribute("book", aBook);

        return "books/new-book";
    }

    @PostMapping("/save")
    public String createProject(Book book, Model model) {
        bookRepository.save(book);
        return "redirect:/books/new";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {

        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);

        return "books/edit-book";
    }

    @PostMapping("/update")
    public String updateBook(Book book){
        bookRepository.save(book);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String removeBook(@PathVariable Long id){
        Book book = bookRepository.findById(id).orElse(null);
        assert book != null;
        bookRepository.delete(book);

        return "redirect:/";
    }
}
