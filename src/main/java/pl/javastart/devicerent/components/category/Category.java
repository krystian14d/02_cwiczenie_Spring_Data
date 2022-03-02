package pl.javastart.devicerent.components.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.javastart.devicerent.components.device.Device;

import javax.persistence.*;
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
