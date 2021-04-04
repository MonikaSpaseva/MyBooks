package monika.projectmanagement.repository;

import monika.projectmanagement.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Long> {
    @Override
    List<Author> findAll();
}
