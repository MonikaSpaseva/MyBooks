package monika.projectmanagement.repository;

import monika.projectmanagement.dto.ChartData;
import monika.projectmanagement.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Query(value = "SELECT * FROM book inner join author on book.author_id = author.id where book.title ilike %:keyword% OR book.genre ilike %:keyword% or author.full_name ilike %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value FROM book GROUP BY stage")
     List<ChartData> getBookStage();
}
