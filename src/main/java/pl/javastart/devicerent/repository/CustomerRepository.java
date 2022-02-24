package pl.javastart.devicerent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.devicerent.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
