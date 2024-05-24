package dev.patika.vetapp.v1.service.concretes;

import dev.patika.vetapp.v1.service.abstracts.AnimalService;
import dev.patika.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import dev.patika.vetapp.v1.core.exception.NotFoundObjectRequest;
import dev.patika.vetapp.v1.core.exception.NotFoundCustomerException;
import dev.patika.vetapp.v1.core.exception.NotFoundException;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.CustomerRepository;
import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.dao.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;


    @Override
    public Animal save(Animal animal) {
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }


    @Override
    public Animal update(Animal animal) {
        animalRepository.findById(animal.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.save(animal);
    }


    @Override
    public Animal getId(long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public List<Animal> getCustomerId(long animalCustomerId) {
        if(animalRepository.findByCustomer_Id(animalCustomerId).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return animalRepository.findByCustomer_Id(animalCustomerId);
    }


    @Override
    public List<Animal> getAnimalByName(String name) { // Section 13 - filter by name
        if(animalRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return animalRepository.findByName(name);
    }

    @Override
    public List<Animal> getCustomerName(String name) {
        if(animalRepository.findByCustomerName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return animalRepository.findByCustomerName(name);
    }

    @Override
    public boolean delete(long id) {
        animalRepository.delete(getId(id));
        return true;
    }
}
