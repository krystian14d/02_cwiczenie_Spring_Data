package pl.javastart.devicerent.components.category;

public class CategoryNotFoundException extends RuntimeException{
    CategoryNotFoundException(String message){
        super(message);
    }
}
