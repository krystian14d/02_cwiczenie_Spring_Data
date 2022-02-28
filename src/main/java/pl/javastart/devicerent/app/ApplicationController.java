package pl.javastart.devicerent.app;

import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.components.category.CategoryController;
import pl.javastart.devicerent.components.customer.CustomerController;
import pl.javastart.devicerent.components.device.DeviceController;
import pl.javastart.devicerent.components.rent.RentController;

import java.util.Arrays;
import java.util.Scanner;

@Controller
public class ApplicationController {
    private Scanner scanner;
    private ConsoleLogger logger;
    private CategoryController categoryController;
    private CustomerController customerController;
    private DeviceController deviceController;
    private RentController rentController;

    public ApplicationController(Scanner scanner, ConsoleLogger logger,
                                 CategoryController categoryController,
                                 CustomerController customerController,
                                 DeviceController deviceController,
                                 RentController rentController) {
        this.scanner = scanner;
        this.logger = logger;
        this.categoryController = categoryController;
        this.customerController = customerController;
        this.deviceController = deviceController;
        this.rentController = rentController;
    }

    public void mainLoop() {
        Options option;
        printOptions();
        option = readOption();
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
        logger.logInfo("Do zobaczenia!");
    }

    private Options readOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while (!correctOptionSelected) {
            logger.logInfo("Podaj ID opcji:");
            int optionId = scanner.nextInt();
            try {
                option = Options.numberToCategory(optionId);
                correctOptionSelected = true;
            } catch (InvalidOptionException e) {
                logger.logErr(e.getMessage());
            }
        }
        return option;
    }

    private void printOptions() {
        logger.logInfo("Opcje:");
        Arrays.stream(Options.values()).forEach(opt -> logger.logInfo(opt.toString()));
    }
}
