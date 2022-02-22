package pl.javastart.devicerent.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Device {

    @Id
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    @ManyToOne
    private Category category;

}
