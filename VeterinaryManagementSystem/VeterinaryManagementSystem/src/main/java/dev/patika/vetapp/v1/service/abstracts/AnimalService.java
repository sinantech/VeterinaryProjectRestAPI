package dev.patika.vetapp.v1.service.abstracts;

import dev.patika.vetapp.v1.entities.Animal;

import java.util.List;


public interface AnimalService {

    Animal save(Animal animal);


    Animal update(Animal animal);


    Animal getId(long id);


    List<Animal> getCustomerId(long animalCustomerId);


    List<Animal> getAnimalByName(String name);


    List<Animal> getCustomerName(String name);


    boolean delete(long id);
}
