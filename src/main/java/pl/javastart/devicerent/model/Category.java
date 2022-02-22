package pl.javastart.devicerent.model;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.List;

public class Category {
    @Id
    private Long id;
    private String name;
    private String description;
    private List<Device> devices = new HashSet<>();
}
