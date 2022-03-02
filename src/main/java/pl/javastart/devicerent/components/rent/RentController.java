package pl.javastart.devicerent.components.rent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.javastart.devicerent.components.customer.Customer;
import pl.javastart.devicerent.components.customer.CustomerRepository;
import pl.javastart.devicerent.components.device.Device;
import pl.javastart.devicerent.components.device.DeviceRepository;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
@Controller
public class RentController {
    private Scanner scanner;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;

    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch (RentException e) {
            log.info(e.getMessage());
        }
    }

    private void rent() {
        log.info("Podaj PESEL klienta:");
        Optional<Customer> customer = customerRepository.findByPesel(scanner.nextLine());
        log.info("Podaj ID urządzenia:");
        Optional<Device> device = deviceRepository.findById(scanner.nextLong());

        if (customer.isPresent())
            device.ifPresentOrElse(addCustomerIfNecessary(customer),
            () -> {
                throw new RentException("Brak urządzenia o wskazanym ID");
            });
        else
            throw new RentException("Brak klienta o wskazanym nr PESEL");
    }

    private Consumer<Device> addCustomerIfNecessary(Optional<Customer> customer){
        return dev -> {
            if (dev.getQuantity() > dev.getCustomers().size())
                dev.addCustomer(customer.get());
            else
                throw new RentException("Brak wolnych urządzeń o wskazanym ID");
        };
    }
}
