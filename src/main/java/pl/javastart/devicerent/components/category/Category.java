package pl.javastart.devicerent.components.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.devicerent.components.customer.device.Device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 1024)
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Device> devices = new HashSet<>();

    @Override
    public String toString() {
        return "Kategoria{" +
                "id=" + id +
                ", nazwa='" + name + '\'' +
                ", opis='" + description + '\'' +
                '}';
    }
}
