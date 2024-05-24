package dev.patika.vetapp.v1.service.concretes;

import dev.patika.vetapp.v1.service.abstracts.CustomerService;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.CustomerRepository;
import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.entities.Customer;
import dev.patika.vetapp.v1.core.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) { // Section 10 - Pet owner registration
        if(customerRepository.existsByMail(customer.getMail()) || customerRepository.existsByPhone(customer.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return customerRepository.save(customer);
    }


    @Override
    public Customer update(Customer customer) {
        customerRepository.findById(customer.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return customerRepository.save(customer);
    }


    @Override
    public Customer getId(long id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public boolean delete(long id) {
        customerRepository.delete(getId(id));
        return true;
    }


    @Override
    public List<Customer> getByCustomerName(String name) { // Section 11 - filter by name
        if(customerRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return customerRepository.findByName(name);
    }


    @Override
    public List<Animal> getByAnimalList(long id) { // Section - 14 Listing owned animals
        if(customerRepository.findById(id).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        if(getId(id).getAnimalList().isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return getId(id).getAnimalList();
    }
}
