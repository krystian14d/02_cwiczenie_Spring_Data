package pl.javastart.devicerent.components.customer;

public class ClientNotFoundException extends IllegalArgumentException{

    public ClientNotFoundException(String s) {
        super(s);
    }

    ClientNotFoundException() {
        super("Nie znaleziono klienta.");
    }
}
