package pl.javastart.devicerent.components.device;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.devicerent.components.category.Category;
import pl.javastart.devicerent.components.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 2048)
    private String description;
    private int quantity;
    private double price;
    //Device owner of the relationship, so here we put @JoinColumn annotation
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", unique = true)
    private Category category;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "device_customers",
            joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.getRentDevices().add(this);
    }

    @Override
    public String toString() {
        return "Urządzenie{" +
                "id=" + id +
                ", nazwa='" + name + '\'' +
                ", opis='" + description + '\'' +
                ", ilość=" + quantity +
                ", cena=" + price + '}';
    }
}

