package dev.patika.vetapp.v1.business.abstracts;

import dev.patika.vetapp.v1.entities.AvailableDate;


public interface AvailableDateService {

    AvailableDate save(AvailableDate availableDate);


    AvailableDate update(AvailableDate availableDate);


    AvailableDate getId(long id);

    boolean delete(long id);

    boolean forceDelete(long id);
}
