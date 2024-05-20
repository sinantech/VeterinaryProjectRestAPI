package dev.patika.vetapp.v1.business.abstracts;

import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.entities.Customer;

import java.util.List;


public interface CustomerService {

    Customer save(Customer customer);


    Customer update(Customer customer);


    Customer getId(long id);


    boolean delete(long id);


    List<Customer> getByCustomerName(String name);


    List<Animal> getByAnimalList(long id);
}
