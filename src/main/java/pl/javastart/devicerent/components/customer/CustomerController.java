package pl.javastart.devicerent.components.customer;

import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.app.ConsoleLogger;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.category.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class CustomerController {
    private Scanner scanner;
    private CustomerRepository customerRepository;
    private ConsoleLogger logger;

    public CustomerController(Scanner scanner, CustomerRepository customerRepository, ConsoleLogger logger) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
        this.logger = logger;
    }

    public void createCustomer() {
        Customer customer = readCustomer();
        customerRepository.save(customer);
        logger.logInfo("Utworzono nowego klienta:");
        customer.toString();
    }

    public void removeCustomer() {
        logger.logInfo("Podaj ID klienta którego chcesz usunąć:");
        long customerId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresentOrElse(customerRepository::delete, () -> logger.logInfo("Nie znaleziono klienta o podanym ID."));
    }

    private Customer readCustomer() {
        Customer customer = new Customer();
        logger.logInfo("Podaj imię klienta:");
        customer.setFirstName(scanner.nextLine());
        logger.logInfo("Podaj nazwisko klienta:");
        customer.setLastName(scanner.nextLine());
        logger.logInfo("Podaj PESEL klienta:");
        customer.setPesel(scanner.nextLine());
        logger.logInfo("Podaj nr dowodu klienta:");
        customer.setIdNumber(scanner.nextLine());
        return customer;
    }
}
