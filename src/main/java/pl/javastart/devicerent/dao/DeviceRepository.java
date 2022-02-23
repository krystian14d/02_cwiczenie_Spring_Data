package pl.javastart.devicerent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.devicerent.model.Device;

import javax.persistence.EntityManager;

@Repository
public class DeviceRepository {

    private final EntityManager entityManager;

    public DeviceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Device save(Device device){
        entityManager.persist(device);
        return device;
    }

    public Device read(Long id){
        return entityManager.find(Device.class, id);
    }

    @Transactional
    public Device update(Device device){
        return entityManager.merge(device);
    }

    @Transactional
    public void delete(Device device){
        Device deviceToRemove = read(device.getId());
        entityManager.remove(deviceToRemove);
    }
}
