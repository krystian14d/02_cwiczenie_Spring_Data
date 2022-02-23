package pl.javastart.devicerent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.devicerent.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CategoryRepository {
    private final EntityManager entityManager;

    public CategoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Category saveCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category read(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public Category update(Category category) {
        return entityManager.merge(category);
    }

    @Transactional
    public void delete(Category category) {
        Category categoryToRemove = read(category.getId());
        entityManager.remove(categoryToRemove);
    }


}
