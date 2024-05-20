package dev.patika.vetapp.v1.business.concretes;

import dev.patika.vetapp.v1.business.abstracts.DoctorService;
import dev.patika.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import dev.patika.vetapp.v1.core.exception.NotFoundException;
import dev.patika.vetapp.v1.core.exception.NotUniqueValues;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.DoctorRepository;
import dev.patika.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DoctorManager implements DoctorService {

    private final DoctorRepository doctorRepository;


    @Override
    public Doctor save(Doctor doctor) { // Section 15 - Registering a doctor
        if(doctorRepository.existsByMailOrPhone(doctor.getMail(),doctor.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return doctorRepository.save(doctor);
    }


    @Override
    public Doctor update(Doctor doctor) {
        doctorRepository.findById(doctor.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return doctorRepository.save(doctor);
    }


    @Override
    public Doctor getId(long id) {
        return doctorRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public boolean delete(long id) {
        doctorRepository.delete(getId(id));
        return true;
    }
}
