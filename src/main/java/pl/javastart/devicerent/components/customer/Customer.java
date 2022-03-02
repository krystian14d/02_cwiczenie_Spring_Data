package pl.javastart.devicerent.components.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.devicerent.components.customer.device.Device;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(length = 11)
    private String pesel;
    @Column(name = "id_number", length = 10)
    private String idNumber;
    @ManyToMany(mappedBy = "customers")
    private List<Device> rentDevices = new ArrayList<>();

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imię='" + firstName + '\'' +
                ", nazwisko='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", nr dowodu='" + idNumber + '\'' +
                '}';
    }
}
