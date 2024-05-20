# API Documentation

This document provides an overview of the API endpoints and their functions.

## Animal Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/animals/{id}                  | GET         | Retrieve the animal with the specified ID.                   |
| /api/v1/animals/{id}                  | PUT         | Update the animal with the specified ID.                     |
| /api/v1/animals/{id}                  | DELETE      | Delete the animal with the specified ID.                     |
| /api/v1/animals                       | GET         | Retrieve all animals.                                        |
| /api/v1/animals                       | POST        | Add a new animal.                                            |
| /api/v1/animals/byName                | GET         | Filter animals by name.                                      |
| /api/v1/animals/byCustomerName        | GET         | Filter animals by the owner's name.                          |

## Customer Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/customers/{id}                | GET         | Retrieve the customer with the specified ID.                 |
| /api/v1/customers/{id}                | PUT         | Update the customer with the specified ID.                   |
| /api/v1/customers/{id}                | DELETE      | Delete the customer with the specified ID.                   |
| /api/v1/customers                     | GET         | Retrieve all customers.                                      |
| /api/v1/customers                     | POST        | Add a new customer.                                          |
| /api/v1/customers/byName              | GET         | Filter customers by name.                                    |

## Vaccine Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/vaccines/{id}                 | GET         | Retrieve the vaccine with the specified ID.                  |
| /api/v1/vaccines/{id}                 | PUT         | Update the vaccine with the specified ID.                    |
| /api/v1/vaccines/{id}                 | DELETE      | Delete the vaccine with the specified ID.                    |
| /api/v1/vaccines                      | GET         | Retrieve all vaccines.                                       |
| /api/v1/vaccines                      | POST        | Add a new vaccine.                                           |

## Appointment Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/appointments/{id}             | GET         | Retrieve the appointment with the specified ID.              |
| /api/v1/appointments/{id}             | PUT         | Update the appointment with the specified ID.                |
| /api/v1/appointments/{id}             | DELETE      | Delete the appointment with the specified ID.                |
| /api/v1/appointments                  | GET         | Retrieve all appointments.                                   |
| /api/v1/appointments                  | POST        | Add a new appointment.                                       |

## Available Date Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/availableDates/{id}           | GET         | Retrieve the available date with the specified ID.           |
| /api/v1/availableDates/{id}           | PUT         | Update the available date with the specified ID.             |
| /api/v1/availableDates/{id}           | DELETE      | Delete the available date with the specified ID.             |
| /api/v1/availableDates                | GET         | Retrieve all available dates.                                |
| /api/v1/availableDates                | POST        | Add a new available date.                                    |

## Doctor Endpoints

| Endpoint                              | HTTP Method | Description                                                  |
|---------------------------------------|-------------|--------------------------------------------------------------|
| /api/v1/doctors/{id}                  | GET         | Retrieve the doctor with the specified ID.                   |
| /api/v1/doctors/{id}                  | PUT         | Update the doctor with the specified ID.                     |
| /api/v1/doctors/{id}                  | DELETE      | Delete the doctor with the specified ID.                     |
| /api/v1/doctors                       | GET         | Retrieve all doctors.                                        |
| /api/v1/doctors                       | POST        | Add a new doctor.                                            |
| /api/v1/doctors/byName                | GET         | Filter doctors by name.                                      |
