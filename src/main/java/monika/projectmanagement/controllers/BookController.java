package monika.projectmanagement.controllers;

import monika.projectmanagement.dto.CreateBookDto;
import monika.projectmanagement.entity.Author;
import monika.projectmanagement.entity.Book;
import monika.projectmanagement.repository.AuthorRepo;
import monika.projectmanagement.repository.BookRepository;
import monika.projectmanagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    AuthorRepo authorRepo;


    @GetMapping("/new")
    public String displayBookForm(Model model) {

        CreateBookDto aBook = new CreateBookDto();
        model.addAttribute("book", aBook);

        return "books/new-book";
    }

    @PostMapping("/save")
    public String createProject(CreateBookDto book, Model model) throws IOException {

        String authorFullName = book.getAuthor().getFullName();
        List<Author> allAuthors = authorRepo.findAll();
        Author existingAuthor = null;

        for (Author a : allAuthors) {
            if (a.getFullName().equals(authorFullName)) {
                existingAuthor = a;
                break;
            }
        }

        String fileName = storageService.saveFile(book.getFile());
        book.setFileUrl("/files/" + fileName);

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
        CreateBookDto createBookDto = new CreateBookDto();
        assert book != null;
        createBookDto.setFileUrl("/files/" + book.getFileUrl());
        createBookDto.setId(book.getId());
        createBookDto.setTitle(book.getTitle());
        createBookDto.setAuthor(book.getAuthor());
        createBookDto.setGenre(book.getGenre());
        createBookDto.setStage(book.getStage());
        createBookDto.setStars(book.getStars());

        model.addAttribute("book", createBookDto);

        return "books/edit-book";
    }

    @PostMapping("/update")
    public String updateBook(CreateBookDto book) throws IOException {

        String fileName = storageService.saveFile(book.getFile());
        Book bookLoadedfromDb  = bookRepository.findById(book.getId()).orElse(null);
        bookLoadedfromDb.setId(book.getId());
        bookLoadedfromDb.setTitle(book.getTitle());
        bookLoadedfromDb.setAuthor(book.getAuthor());
        bookLoadedfromDb.setGenre(book.getGenre());
        bookLoadedfromDb.setStage(book.getStage());
        bookLoadedfromDb.setStars(book.getStars());
        bookLoadedfromDb.setFileUrl("/files/" + fileName);

        bookRepository.save(bookLoadedfromDb);

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
