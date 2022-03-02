package pl.javastart.devicerent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.devicerent.app.ApplicationService;

@SpringBootApplication
public class DevicerentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DevicerentApplication.class, args);
        ApplicationService appController = context.getBean(ApplicationService.class);
        appController.mainLoop();
    }
}
