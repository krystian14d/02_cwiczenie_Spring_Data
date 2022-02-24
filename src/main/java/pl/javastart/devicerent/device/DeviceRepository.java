package pl.javastart.devicerent.device;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerent.device.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
