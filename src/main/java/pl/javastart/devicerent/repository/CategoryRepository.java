package pl.javastart.devicerent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerent.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
