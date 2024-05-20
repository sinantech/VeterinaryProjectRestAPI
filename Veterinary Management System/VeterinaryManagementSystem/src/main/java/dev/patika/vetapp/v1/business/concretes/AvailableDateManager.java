package dev.patika.vetapp.v1.business.concretes;

import dev.patika.vetapp.v1.business.abstracts.AvailableDateService;
import dev.patika.vetapp.v1.core.exception.AppointmentAlreadyExists;
import dev.patika.vetapp.v1.core.exception.NotFoundDoctorException;
import dev.patika.vetapp.v1.core.exception.NotFoundException;
import dev.patika.vetapp.v1.core.exception.SameValuesException;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.AppointmentRepository;
import dev.patika.vetapp.v1.dao.AvailableRepository;
import dev.patika.vetapp.v1.dao.DoctorRepository;
import dev.patika.vetapp.v1.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AvailableDateManager implements AvailableDateService {
    private final AvailableRepository availableRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public AvailableDate save(AvailableDate availableDate) { // Section 16 - Save doctor available day
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return availableRepository.save(availableDate);
    }


    @Override
    public AvailableDate update(AvailableDate availableDate) {
        if(appointmentRepository.existsByAvailableDate_Id(availableDate.getId())){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.findById(availableDate.getId()).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return availableRepository.save(availableDate);
    }


    @Override
    public AvailableDate getId(long id) {
        return availableRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public boolean delete(long id) {
        if(appointmentRepository.existsByAvailableDate_Id(id)){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.delete(getId(id));
        return true;
    }

    @Override
    public boolean forceDelete(long id) {
        availableRepository.delete(getId(id));
        return true;
    }

}
