package pl.javastart.devicerent.components.category;

public class CategoryNotFoundException extends IllegalArgumentException {

    CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("Kategoria nie istnieje");
    }
}
