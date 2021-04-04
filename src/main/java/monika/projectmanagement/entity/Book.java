package monika.projectmanagement.entity;

import javax.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
    @Column(name = "genre")
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage")
    private BookStatus stage;


    public Book() {
    }

    public Book(String title, String genre, BookStatus stage) {
        this.title = title;
        this.genre = genre;
        this.stage = stage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BookStatus getStage() {
        return stage;
    }

    public void setStage(BookStatus stage) {
        this.stage = stage;
    }




}
