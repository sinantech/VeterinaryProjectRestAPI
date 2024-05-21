package dev.patika.vetapp.v1.business.concretes;

import dev.patika.vetapp.v1.business.abstracts.VaccineService;
import dev.patika.vetapp.v1.core.utilities.Message;
import dev.patika.vetapp.v1.dao.AnimalRepository;
import dev.patika.vetapp.v1.dao.VaccineRepository;
import dev.patika.vetapp.v1.entities.Vaccine;
import dev.patika.vetapp.v1.core.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;


    @Override
    public Vaccine addVaccine(Vaccine vaccine) { // Section 21 - Vaccine registration
        if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){ // Section 22 - Vaccine day check
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                return vaccineRepository.save(vaccine);
            }
            throw new DateMistmatchException(Message.DATE_MISMATCH);
        }
        if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return vaccineRepository.save(vaccine);
    }


    @Override
    public Vaccine getId(long id) {
        return vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public Vaccine update(Vaccine vaccine) {
        getId(vaccine.getId());
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                return vaccineRepository.save(vaccine);
            }
            throw new ForceUpdateException(Message.FORCE_UPDATE);
        }
        return addVaccine(vaccine);
    }


    @Override
    public Vaccine forceUpdate(Vaccine vaccine) {
        getId(vaccine.getId());
        if(!animalRepository.existsById(vaccine.getAnimal().getId())){
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccine.getEndDate().isBefore(vaccine.getStartDate())) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return vaccineRepository.save(vaccine);
    }


    @Override
    public boolean delete(long id) {
        vaccineRepository.delete(getId(id));
        return true;
    }


    @Override
    public List<Vaccine> getAnimalVaccineList(long id) { // Section 24 - filter by animal vaccines
        if (vaccineRepository.findByAnimal_Id(id).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        return vaccineRepository.findByAnimal_Id(id);
    }

    @Override
    public List<Vaccine> getFilterByStartAndEndDate(LocalDate firstDate,LocalDate endDate) { // Section 23 - filter by end date
        if (vaccineRepository.findByEndDateBetween(firstDate,endDate).isEmpty()) {
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return vaccineRepository.findByEndDateBetween(firstDate,endDate);
    }

}
