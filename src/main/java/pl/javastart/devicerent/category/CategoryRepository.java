package pl.javastart.devicerent.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerent.category.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
