package pl.javastart.devicerent.components.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
@Slf4j
@Controller
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository categoryRepository;

    public void createCategory() {
        Category category = readCategory();
        try {
            categoryRepository.save(category);
            log.info("Dodano kategorię:");
            category.toString();
        } catch (DataIntegrityViolationException e) {
            log.error("Nie dodano kategorii, możliwe że nazwa jest duplikatem.");
        }
    }

    public void removeCategory() {
        log.info("Podaj ID kategorii którą chcesz usunąć:");
        long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresentOrElse(categoryRepository::delete, CategoryNotFoundException::new);

    }

    private Category readCategory() {
        Category category = new Category();
        log.info("Podaj nazwę kategorii:");
        category.setName(scanner.nextLine());
        log.info("Podaj opis kategorii:");
        category.setDescription(scanner.nextLine());
        return category;
    }
}
