package pl.javastart.devicerent.components.device;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.category.CategoryNotFoundException;
import pl.javastart.devicerent.components.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@AllArgsConstructor
@Service
public class DeviceService {
    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    public void createDevice() {
        try {
            Device device = readDevice();
            deviceRepository.save(device);
            log.info("Dodano urządzenie");
            device.toString();
        } catch (CategoryNotFoundException e) {
            log.error("Urządzenia nie dodano. " + e.getMessage());
        }
    }

    public void searchDevice() {
        log.info("Podaj nazwę urządzenia:");
        String name = scanner.nextLine();
        List<Device> devices = deviceRepository.findAllByNameContainingIgnoreCase(name);
        if (CollectionUtils.isEmpty(devices)) {
            log.info("Nie znaleziono urządzeń o podanej nazwie");
        } else {
            log.info("Znalezione urządzenia:");
            devices.forEach(device -> log.info(device.toString()));
        }
    }

    public void removeDevice() {
        log.info("Podaj ID urządzenia które chcesz usunąć:");
        long deviceId = scanner.nextLong();
        Optional<Device> deviceToRemove = deviceRepository.findById(deviceId);
        deviceToRemove.ifPresentOrElse(deviceRepository::delete, DeviceNotFoundException::new);
    }

    private Device readDevice() {
        Device device = new Device();
        readDeviceDataFromUser(device);
        return device;
    }

    private void readDeviceDataFromUser(Device device) {
        log.info("Podaj nazwę urządzenia:");
        device.setName(scanner.nextLine());
        log.info("Podaj opis urządzenia:");
        device.setDescription(scanner.nextLine());
        log.info("Podaj cenę urządzenia:");
        device.setPrice(scanner.nextDouble());
        scanner.nextLine();
        log.info("Podaj ilość urządzeń:");
        device.setQuantity(scanner.nextInt());
        scanner.nextLine();
        log.info("Podaj nazwę kategorii dla urządzenia:");
        String categoryName = scanner.nextLine();
        Optional<Category> foundCategory = categoryRepository.findByNameIgnoreCase(categoryName);
        foundCategory.ifPresentOrElse(device::setCategory,CategoryNotFoundException::new);
    }
}
