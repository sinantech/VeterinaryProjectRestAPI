package dev.patika.vetapp.v1.controller;

import dev.patika.vetapp.v1.service.abstracts.AnimalService;
import dev.patika.vetapp.v1.service.abstracts.AppointmentService;
import dev.patika.vetapp.v1.service.abstracts.DoctorService;
import dev.patika.vetapp.v1.core.result.Result;
import dev.patika.vetapp.v1.core.result.ResultData;
import dev.patika.vetapp.v1.core.config.modelMapper.ModelMapperService;
import dev.patika.vetapp.v1.core.utilities.ResultHelper;
import dev.patika.vetapp.v1.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetapp.v1.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vetapp.v1.dto.response.appointment.AppointmentGetAllResponse;
import dev.patika.vetapp.v1.dto.response.appointment.AppointmentResponse;
import dev.patika.vetapp.v1.entities.Animal;
import dev.patika.vetapp.v1.entities.Appointment;
import dev.patika.vetapp.v1.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final ModelMapperService modelMapperService;
    private final AnimalService animalService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        // Add a new appointment and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(appointmentService.addAppointments(modelMapperService.forRequest().map(appointmentSaveRequest, Appointment.class)), AppointmentResponse.class));
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        // Update an existing appointment and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(appointmentService.update(modelMapperService.forRequest().map(appointmentUpdateRequest, Appointment.class)), AppointmentResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentGetAllResponse> getId(@PathVariable("id") long id) {
        // Retrieve an appointment by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(appointmentService.getId(id), AppointmentGetAllResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        // Delete an appointment by its ID.
        return ResultHelper.DELETE(appointmentService.delete(id));
    }


    @GetMapping("/appointments-date-doctor")
    public ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndDoctor(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                               @RequestParam("doctorId") long id) {
        Doctor doctor = doctorService.getId(id);
        // Filter appointments by date and doctor, then map the results to response objects.
        return ResultHelper.OK(appointmentService.filterDateTimeAndDoctor(startDate, endDate, doctor).stream().map(appointment -> modelMapperService.forResponse().map(appointment, AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }


    @GetMapping("/appointments-date-animal")
    public ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                               @RequestParam("animalId") long id) {
        Animal animal = animalService.getId(id);
        // Filter appointments by date and animal, then map the results to response objects.
        return ResultHelper.OK(appointmentService.filterDateTimeAndAnimal(startDate, endDate, animal).stream().map(appointment -> modelMapperService.forResponse().map(appointment, AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }
}
