package pl.javastart.devicerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.devicerent.app.ApplicationController;
import pl.javastart.devicerent.components.device.DeviceRepository;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.customer.Customer;
import pl.javastart.devicerent.components.device.Device;

@SpringBootApplication
public class DevicerentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicerentApplication.class, args);
        ApplicationController appController = context.getBean(ApplicationController.class);
        appController.mainLoop();3
    }
}
