package pl.javastart.devicerent.model;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String idNumber;
    private List<Device> devices = new ArrayList<>();
}
