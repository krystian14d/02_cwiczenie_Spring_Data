package pl.javastart.devicerent.components.category;

public class CategoryNotFoundException extends IllegalArgumentException {

    public CategoryNotFoundException() {
        super("Kategoria nie istnieje");
    }

    CategoryNotFoundException(String message) {
        super(message);
    }
}
