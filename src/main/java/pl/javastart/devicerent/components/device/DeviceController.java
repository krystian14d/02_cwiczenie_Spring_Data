package pl.javastart.devicerent.components.device;

import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.app.ConsoleLogger;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.category.CategoryRepository;

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
        logger.logInfo("Podaj ID kategorii dla urządzenia:");
        long categoryId = scanner.nextLong();
        scanner.nextLine();
        Optional<Category> foundCategory = categoryRepository.findById(categoryId);
        foundCategory.ifPresentOrElse(device::setCategory,
                () -> {
                    throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje");
                }
        );
        return device;
    }
}
