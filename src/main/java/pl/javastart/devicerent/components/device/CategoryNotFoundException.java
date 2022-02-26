package pl.javastart.devicerent.components.device;

public class CategoryNotFoundException extends RuntimeException{
    CategoryNotFoundException(String message){
        super(message);
    }
}
