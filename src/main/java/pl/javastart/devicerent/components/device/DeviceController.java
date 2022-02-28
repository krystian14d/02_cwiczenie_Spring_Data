package pl.javastart.devicerent.components.device;

import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.app.ConsoleLogger;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class DeviceController {
    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private ConsoleLogger logger;
    private CategoryRepository categoryRepository;

    public DeviceController(Scanner scanner, DeviceRepository deviceRepository, ConsoleLogger logger, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.logger = logger;
        this.categoryRepository = categoryRepository;
    }

    public void createDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            logger.logInfo("Dodano urządzenie");
            device.toString();
        } catch (CategoryNotFoundException e) {
            logger.logErr("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    public void searchDevice() {
        logger.logInfo("Podaj nazwę urządzenia:");
        String name = scanner.nextLine();
        List<Device> devices = deviceRepository.findAllByNameContainingIgnoreCase(name);
        if (devices.isEmpty()) {
            logger.logInfo("Nie znaleziono urządzeń o podanej nazwie");
        } else {
            logger.logInfo("Znalezione urządzenia:");
            devices.forEach(device -> logger.logInfo(device.toString()));
        }
    }

    public void removeDevice() {
        logger.logInfo("Podaj ID urządzenia które chcesz usunąć:");
        long deviceId = scanner.nextLong();
        Optional<Device> deviceToRemove = deviceRepository.findById(deviceId);
        deviceToRemove.ifPresentOrElse(deviceRepository::delete,
                () -> logger.logInfo("Brak urządzenia o wskazanym ID."));
    }

    private Device readDevice() {
        Device device = new Device();
        logger.logInfo("Podaj nazwę urządzenia:");
        device.setName(scanner.nextLine());
        logger.logInfo("Podaj opis urządzenia:");
        device.setDescription(scanner.nextLine());
        logger.logInfo("Podaj cenę urządzenia:");
        device.setPrice(scanner.nextDouble());
        scanner.nextLine();
        logger.logInfo("Podaj ilość urządzeń:");
        device.setQuantity(scanner.nextInt());
        scanner.nextLine();
        logger.logInfo("Podaj nazwę kategorii dla urządzenia:");
        String categoryName = scanner.nextLine();
        Optional<Category> foundCategory = categoryRepository.findByNameIgnoreCase(categoryName);
        foundCategory.ifPresentOrElse(device::setCategory,
                () -> {
                    throw new CategoryNotFoundException("Kategoria o podanej nie istnieje");
                }
        );
        return device;
    }
}
