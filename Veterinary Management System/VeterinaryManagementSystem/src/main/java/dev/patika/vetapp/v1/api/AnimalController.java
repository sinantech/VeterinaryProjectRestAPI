package dev.patika.vetapp.v1.api;

import dev.patika.vetapp.v1.business.abstracts.AnimalService;
import dev.patika.vetapp.v1.core.result.Result;
import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.core.config.modelMapper.ModelMapperService;
import dev.patika.vetapp.v1.core.utilities.ResultHelper;
import dev.patika.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetapp.v1.dto.response.animal.AnimalGetAllResponse;
import dev.patika.vetapp.v1.dto.response.animal.AnimalResponse;
import dev.patika.vetapp.v1.entities.Animal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ModelMapperService modelMapperService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalGetAllResponse> getId(@PathVariable("id") long id) {
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.getId(id), AnimalGetAllResponse.class));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody() AnimalSaveRequest animalSaveRequest) {
        return ResultHelper.CREATED(modelMapperService.forResponse().map(animalService.save(modelMapperService.forRequest().map(animalSaveRequest, Animal.class)), AnimalResponse.class));
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        animalService.update(modelMapperService.forRequest().map(animalUpdateRequest, Animal.class));
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.update(modelMapperService.forRequest().map(animalUpdateRequest, Animal.class)), AnimalResponse.class));
    }


    @GetMapping("/by-customer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalGetAllResponse> getCustomerId(@PathVariable("id") long id) {
        return animalService.getCustomerId(id).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList());
    }


    @GetMapping("/by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getAnimalByName(@RequestParam String name) {
        return ResultHelper.OK(animalService.getAnimalByName(name).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }


    @GetMapping("/by-customer-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getByCustomerName(@RequestParam String name) {
        return ResultHelper.OK(animalService.getCustomerName(name).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable long id) {
        return ResultHelper.DELETE(animalService.delete(id));
    }
}
