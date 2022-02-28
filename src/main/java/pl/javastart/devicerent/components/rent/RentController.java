package pl.javastart.devicerent.components.rent;

import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.app.ConsoleLogger;
import pl.javastart.devicerent.components.customer.Customer;
import pl.javastart.devicerent.components.customer.CustomerRepository;
import pl.javastart.devicerent.components.device.Device;
import pl.javastart.devicerent.components.device.DeviceRepository;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class RentController {
    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;
    private ConsoleLogger logger;

    public RentController(Scanner scanner, DeviceRepository deviceRepository, CustomerRepository customerRepository, ConsoleLogger logger) {
        this.scanner = scanner;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
        this.logger = logger;
    }

    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch (RentException e) {
            logger.logErr(e.getMessage());
        }
    }

    private void rent() {
        logger.logInfo("Podaj PESEL klienta:");
        Optional<Customer> customer = customerRepository.findByPesel(scanner.nextLine());
        logger.logInfo("Podaj ID urządzenia:");
        Optional<Device> device = deviceRepository.findById(scanner.nextLong());
        if (customer.isPresent())
            device.ifPresentOrElse(dev -> {
                if (dev.getQuantity() > dev.getCustomers().size())
                    dev.addCustomer(customer.get());
                else
                    throw new RentException("Brak wolnych urządzeń o wskazanym ID");
            }, () -> {
                throw new RentException("Brak urządzenia o wskazanym ID");
            });
        else
            throw new RentException("Brak klienta o wskazanym nr PESEL");
    }
}
