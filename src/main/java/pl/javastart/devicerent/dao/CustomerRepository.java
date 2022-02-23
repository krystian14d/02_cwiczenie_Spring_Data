package pl.javastart.devicerent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.devicerent.model.Customer;

import javax.persistence.EntityManager;

@Repository
public class CustomerRepository {

    private final EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public Customer read(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Transactional
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        Customer customerToRemove = read(customer.getId());
        entityManager.remove(customerToRemove);
    }

}
