package pl.javastart.devicerent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerent.model.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
