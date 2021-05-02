package monika.projectmanagement.controllers;

import monika.projectmanagement.entity.Author;
import monika.projectmanagement.entity.Book;
import monika.projectmanagement.repository.AuthorRepo;
import monika.projectmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepo authorRepo;


    @GetMapping("/new")
    public String displayBookForm(Model model) {

        Book aBook = new Book();
        model.addAttribute("book", aBook);

        return "books/new-book";
    }

    @PostMapping("/save")
    public String createProject(Book book, Model model) {
        // find an author by given full_name
        // if author exists add the current book to his list of books and save the entity
        // if author does not exist add both the author and the book

        String authorFullName = book.getAuthor().getFullName();
        List<Author> allAuthors = authorRepo.findAll();
        Author existingAuthor = null;

        for (Author a : allAuthors) {
            if (a.getFullName().equals(authorFullName)) {
                existingAuthor = a;
                break;
            }
        }

        if (existingAuthor != null) {
            book.setAuthor(existingAuthor);
            existingAuthor.addBook(book);
            authorRepo.save(existingAuthor);
        } else {
            Author newAuthor = book.getAuthor();
            newAuthor.addBook(book);
            authorRepo.save(newAuthor);
        }



        return "redirect:/books/new";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {

        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);

        return "books/edit-book";
    }

    @PostMapping("/update")
    public String updateBook(Book book) {
        bookRepository.save(book);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String removeBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        assert book != null;
        bookRepository.delete(book);

        return "redirect:/";
    }
}
