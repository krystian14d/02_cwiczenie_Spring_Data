package pl.javastart.devicerent.components.category;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class CategoryController {
    private Scanner scanner;
    private CategoryRepository categoryRepository;

    public CategoryController(Scanner scanner, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
    }

    //createCategory()
    //removeCategory()

    private Category readCategory(){

    }
}
