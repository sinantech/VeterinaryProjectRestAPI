package dev.patika.vetapp.v1.controller;

import dev.patika.vetapp.v1.service.abstracts.CustomerService;
import dev.patika.vetapp.v1.core.result.Result;
import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.core.config.modelMapper.ModelMapperService;
import dev.patika.vetapp.v1.core.utilities.ResultHelper;
import dev.patika.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetapp.v1.dto.response.animal.AnimalResponse;
import dev.patika.vetapp.v1.dto.response.customer.CustomerResponse;
import dev.patika.vetapp.v1.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        // Save a new customer and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(customerService.save(modelMapperService.forRequest().map(customerSaveRequest, Customer.class)), CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getId(@PathVariable("id") long id) {
        // Retrieve a customer by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.getId(id), CustomerResponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        // Update an existing customer and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.update(modelMapperService.forRequest().map(customerUpdateRequest, Customer.class)), CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        // Delete a customer by its ID.
        return ResultHelper.DELETE(customerService.delete(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getByCustomerName(@RequestParam String name) {
        // Retrieve customers by name and map the results to response objects.
        return ResultHelper.OK(customerService.getByCustomerName(name).stream().map(customer -> modelMapperService.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList()));
    }

    @GetMapping("/by-animal-list/{id}")
    public ResultData<List<AnimalResponse>> getByAnimalList(@PathVariable("id") long id) {
        // Retrieve customers by associated animal ID and map the results to response objects.
        return ResultHelper.OK(customerService.getByAnimalList(id).stream().map(customer -> modelMapperService.forResponse().map(customer, AnimalResponse.class)).collect(Collectors.toList()));
    }
}
