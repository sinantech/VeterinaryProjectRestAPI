package dev.patika.vetapp.v1.service.abstracts;

import dev.patika.vetapp.v1.entities.Doctor;


public interface DoctorService {

    Doctor save(Doctor doctor);


    Doctor update(Doctor doctor);


    Doctor getId(long id);


    boolean delete(long id);
}
