package dev.patika.vetapp.v1.service.abstracts;

import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.entities.Appointment;
import dev.patika.vetapp.v1.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment addAppointments(Appointment appointment);


    Appointment update(Appointment appointment);


    Appointment getId(long id);


    List<Appointment> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);


    List<Appointment> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);


    boolean delete(long id);
}
