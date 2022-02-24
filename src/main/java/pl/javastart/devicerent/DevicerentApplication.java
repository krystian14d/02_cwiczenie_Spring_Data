package pl.javastart.devicerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.devicerent.device.DeviceRepository;
import pl.javastart.devicerent.category.Category;
import pl.javastart.devicerent.customer.Customer;
import pl.javastart.devicerent.device.Device;

@SpringBootApplication
public class DevicerentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicerentApplication.class, args);
        DeviceRepository deviceRepository = context.getBean(DeviceRepository.class);

        Device device = new Device();
        device.setName("Wiertarka udarowa");
        device.setDescription("Wiertarka udarowa o dużej mocy 3000W z zestawem wierteł w komplecie");
        device.setPrice(80);
        device.setQuantity(5);

        Category category = new Category();
        category.setName("Elektronarzędzia");
        category.setDescription("Wiertarki, szlifierki, młoty udarowe i inne elektronarzędzia");

        Customer customer = new Customer();
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setPesel("90908765123");
        customer.setIdNumber("ABC678123");

        device.setCategory(category);
        device.addCustomer(customer);

        deviceRepository.save(device);
    }
}
