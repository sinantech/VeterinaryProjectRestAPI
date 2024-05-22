package dev.patika.vetapp.v1.business.concretes;

import dev.patika.vetapp.v1.business.abstracts.AppointmentService;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.AnimalRepository;
import dev.patika.vetapp.v1.dao.AppointmentRepository;
import dev.patika.vetapp.v1.dao.AvailableRepository;
import dev.patika.vetapp.v1.dao.DoctorRepository;
import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.entities.Appointment;
import dev.patika.vetapp.v1.entities.AvailableDate;
import dev.patika.vetapp.v1.entities.Doctor;
import dev.patika.vetapp.v1.core.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentManager implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableRepository availableRepository;
    private final AnimalRepository animalRepository;


    @Override
    public Appointment addAppointments(Appointment appointment) { // Section 18 - Save an appointment
        // Check if the specified doctor and animal exist
        if (!doctorRepository.existsById(appointment.getDoctor().getId()) || !animalRepository.existsById(appointment.getAnimal().getId())) {
            throw new NotFoundException(Message.NOT_FOUND_ID);
        }
        // Check if the doctor is available on the specified date
        if(availableRepository.findByAvailableIdInEndDateAndDoctorId(appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId()) == null){
            throw new DoctorDaysConflictException(Message.DAYS_CONFLICT);
        }
        // Find the available date ID for the appointment's date and doctor
        Long availableId = availableRepository.findByAvailableIdInEndDateAndDoctorId(appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId());

        // Check if the available date exists for the specified date and doctor
        if (availableRepository.existsByIdAndDateAndDoctors_Id(availableId, appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId())) {
            // Check for appointment conflicts
            for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
                if (appointmentRepository.existsByDoctor_Id(appointment.getDoctor().getId())) {
                    if (Duration.between(appointment.getDateTime(), appointmentRepository.findAll().get(i).getDateTime()).toHours() == 0) { // Section 18 - Save an appointment
                        throw new AppointmentConflictException(Message.APPOINTMENT_CONFLICT);
                    }
                }
            }
            //Associate the appointment with the available date and save it
            AvailableDate availableDate = availableRepository.findById(availableId).orElseThrow();
            appointment.setAvailableDate(availableDate);
            return appointmentRepository.save(appointment);
        }
        //If no available date is found, throw a conflict exception
        throw new DoctorDaysConflictException(Message.DAYS_CONFLICT);
    }


    @Override
    public Appointment update(Appointment appointment) {
        getId(appointment.getId());
        return addAppointments(appointment);
    }


    @Override
    public Appointment getId(long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public boolean delete(long id) {
        appointmentRepository.delete(getId(id));
        return true;
    }


    @Override
    public List<Appointment> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) { // Section 20 - filter by doctor id and date
        if (doctorRepository.findById(doctor.getId()).isEmpty()) {
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if (!appointmentRepository.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepository.findByDateTimeBetweenAndDoctor(startDate, endDate, doctor);
    }


    @Override
    public List<Appointment> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) { // Section 20 - filter by animal id and date
        if (animalRepository.findById(animal.getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (!appointmentRepository.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        return appointmentRepository.findByDateTimeBetweenAndAnimal(startDate, endDate, animal);
    }
}
