package monika.projectmanagement.repository;

import monika.projectmanagement.dto.ChartData;
import monika.projectmanagement.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value FROM book GROUP BY stage")
     List<ChartData> getBookStage();
}
