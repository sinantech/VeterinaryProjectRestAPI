package dev.patika.vetapp.v1.service.concretes;

import dev.patika.vetapp.v1.service.abstracts.VaccineService;
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
        // Check if the specified animal exists in the repository
        if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        // Check if a vaccine with the same code, name, and animal ID already exists
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            // Check if there are any vaccines with an end date after the specified start date
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){// Section 22 - Vaccine day check
                //  if the end date is before the start date
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                // Save the vaccine if all checks pass
                return vaccineRepository.save(vaccine);
            }
            // Throw an exception if there's a date mismatch
            throw new DateMistmatchException(Message.DATE_MISMATCH);
        }
        // Check if the end date is before the start date
        if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        // Save the vaccine if all checks pass
        return vaccineRepository.save(vaccine);
    }


    @Override
    public Vaccine getId(long id) {
        return vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
    }


    @Override
    public Vaccine update(Vaccine vaccine) {
        // Verify the existence of the vaccine by ID
        getId(vaccine.getId());
        // Check if a vaccine with the same code, name, and animal ID already exists
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            // Check if there are any vaccines with an end date after the specified start date
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){
                // Check if the end date is before the start date
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                // Save the updated vaccine if all checks pass
                return vaccineRepository.save(vaccine);
            }
            // Throw an exception if force update is required
            throw new ForceUpdateException(Message.FORCE_UPDATE);
        }
        // If the vaccine does not exist, add it as a new vaccine
        return addVaccine(vaccine);
    }


    @Override
    public Vaccine forceUpdate(Vaccine vaccine) {
        // Verify the existence of the vaccine by ID
        getId(vaccine.getId());
        // Check if the specified animal exists in the repository
        if(!animalRepository.existsById(vaccine.getAnimal().getId())){
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        // Validate that the end date is not before the start date
        if (vaccine.getEndDate().isBefore(vaccine.getStartDate())) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        // Save the vaccine if all checks pass
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
