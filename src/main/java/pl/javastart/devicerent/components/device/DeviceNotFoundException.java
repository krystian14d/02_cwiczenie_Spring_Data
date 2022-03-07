package pl.javastart.devicerent.components.device;

public class DeviceNotFoundException extends IllegalArgumentException {

    public DeviceNotFoundException(String s) {
        super(s);
    }

    public DeviceNotFoundException() {
        super("Nie znaleziono takiego urządzenia.");
    }
}
