package pl.javastart.devicerent.components.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
@Slf4j
@Service
public class CustomerService {
    private Scanner scanner;
    private CustomerRepository customerRepository;

    public void createCustomer() {
        Customer customer = readCustomer();
        customerRepository.save(customer);
        log.info("Utworzono nowego klienta:");
        customer.toString();
    }

    public void removeCustomer() {
        log.info("Podaj ID klienta którego chcesz usunąć:");
        long customerId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresentOrElse(customerRepository::delete, ClientNotFoundException::new);
    }

    private Customer readCustomer() {
        Customer customer = new Customer();
        log.info("Podaj imię klienta:");
        customer.setFirstName(scanner.nextLine());
        log.info("Podaj nazwisko klienta:");
        customer.setLastName(scanner.nextLine());
        log.info("Podaj PESEL klienta:");
        customer.setPesel(scanner.nextLine());
        log.info("Podaj nr dowodu klienta:");
        customer.setIdNumber(scanner.nextLine());
        return customer;
    }
}
