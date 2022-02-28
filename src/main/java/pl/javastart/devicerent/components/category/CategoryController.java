package pl.javastart.devicerent.components.category;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.app.ConsoleLogger;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class CategoryController {
    private Scanner scanner;
    private CategoryRepository categoryRepository;
    private ConsoleLogger logger;


    public CategoryController(Scanner scanner, CategoryRepository categoryRepository, ConsoleLogger logger) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
        this.logger = logger;
    }

    public void createCategory() {
        Category category = readCategory();
        try {
            categoryRepository.save(category);
            logger.logInfo("Dodano kategorię:");
            category.toString();
        } catch (DataIntegrityViolationException e) {
            logger.logErr("Nie dodano kategorii, możliwe że nazwa jest duplikatem.");
        }
    }

    public void removeCategory() {
        logger.logInfo("Podaj ID kategorii którą chcesz usunąć:");
        long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresentOrElse(categoryRepository::delete, () -> logger.logInfo("Brak podanej kategorii"));

    }

    private Category readCategory() {
        Category category = new Category();
        logger.logInfo("Podaj nazwę kategorii:");
        category.setName(scanner.nextLine());
        logger.logInfo("Podaj opis kategorii:");
        category.setDescription(scanner.nextLine());
        return category;
    }
}
