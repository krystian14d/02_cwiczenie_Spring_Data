package pl.javastart.devicerent.app;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.components.category.CategoryController;
import pl.javastart.devicerent.components.customer.CustomerController;
import pl.javastart.devicerent.components.device.DeviceController;
import pl.javastart.devicerent.components.rent.RentController;

import java.util.Arrays;
import java.util.Scanner;

@AllArgsConstructor
@Slf4j
@Controller
public class ApplicationController {

    private Scanner scanner;
    private CategoryController categoryController;
    private CustomerController customerController;
    private DeviceController deviceController;
    private RentController rentController;

    public void mainLoop() {
        printOptions();
        Options option = readOption();
        executeOption(option);
    }

    private void executeOption(Options option) {
        switch (option) {
            case ADD_DEVICE -> deviceController.createDevice();
            case ADD_CATEGORY -> categoryController.createCategory();
            case ADD_CUSTOMER -> customerController.createCustomer();
            case RENT -> rentController.rentDeviceToCustomer();
            case SEARCH_DEVICE -> deviceController.searchDevice();
            case REMOVE_DEVICE -> deviceController.removeDevice();
            case REMOVE_CATEGORY -> categoryController.removeCategory();
            case REMOVE_CUSTOMER -> customerController.removeCustomer();
            case END -> closeApp();
        }
    }

    private void closeApp() {
        scanner.close();
        log.info("Do zobaczenia!");
    }

    private Options readOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while (!correctOptionSelected) {
            log.info("Podaj ID opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = Options.numberToCategory(optionId);
                correctOptionSelected = true;
            } catch (InvalidOptionException e) {
                log.error(e.getMessage());
            }
        }
        return option;
    }

    private void printOptions() {
        log.info("Opcje:");
        Arrays.stream(Options.values()).forEach(opt -> log.info(opt.toString()));
    }
}
