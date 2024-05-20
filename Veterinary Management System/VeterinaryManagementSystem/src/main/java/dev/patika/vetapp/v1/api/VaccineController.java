package dev.patika.vetapp.v1.api;

import dev.patika.vetapp.v1.business.abstracts.VaccineService;
import dev.patika.vetapp.v1.core.result.Result;
import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.core.config.modelMapper.ModelMapperService;
import dev.patika.vetapp.v1.core.utilities.ResultHelper;
import dev.patika.vetapp.v1.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vetapp.v1.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vetapp.v1.dto.response.vaccine.VaccineGetAllResponse;
import dev.patika.vetapp.v1.dto.response.vaccine.VaccineResponse;
import dev.patika.vetapp.v1.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;
    private final ModelMapperService mapperService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> addVaccine(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        // Add a new vaccine and map the result to a response object.
        return ResultHelper.CREATED(mapperService.forResponse().map(vaccineService.addVaccine(mapperService.forRequest().map(vaccineSaveRequest, Vaccine.class)), VaccineResponse.class));
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        // Update an existing vaccine and map the result to a response object.
        return ResultHelper.OK(mapperService.forResponse().map(vaccineService.update(mapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class)), VaccineResponse.class));
    }


    @PutMapping("/force-update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> forceUpdate(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return ResultHelper.RESOLVE(mapperService.forResponse().map(vaccineService.forceUpdate(mapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class)), VaccineResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineGetAllResponse> getId(@PathVariable long id) {
        // Retrieve a vaccine by its ID and map the result to a response object.
        return ResultHelper.OK(mapperService.forResponse().map(vaccineService.getId(id), VaccineGetAllResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        // Delete a vaccine by its ID.
        return ResultHelper.DELETE(vaccineService.delete(id));
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> getAnimalVaccineList(@PathVariable("id") long id) {
        // Retrieve vaccines associated with a specific animal and map the result to response objects.
        return ResultHelper.OK(vaccineService.getAnimalVaccineList(id).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-date") // 1
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> getFilterByStartAndEndDate(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firsDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Retrieve a list of vaccines filtered by end date and map the result to response objects.
        return ResultHelper.OK(vaccineService.getFilterByStartAndEndDate(firsDate, endDate).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }
}