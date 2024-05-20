# Veterinary Management System Examples and Running Project

This project is a REST API project that manages vaccinations, animals, customers, and appointments in a veterinary clinic.

## Starting

The following instructions will help you run and develop the project on your local machine.

### Prerequisites

To run the project, the following software must be installed
- Java 11 or higher.
- Maven 3.2 or higher

### Installing

1. Clone this project:
   ```bash
   git clone https://github.com/sinantech/proje-adı.git
   ```
2. Navigate the project directory:
   ```bash
   cd veterinary management system
   ```
3. Compile the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the project:
   ```bash
   java -jar target/veterinarymanagementsystem.jar
   ```

## Endpoints

### Customer Operations

#### Customer Create

- **URL:** `/api/customers`
- **Method:** POST
- **Explanation:** Creates New Customer.
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "phone": "1234567890",
    "mail": "john@example.com",
    "address": "123 Main Street",
    "city": "New York"
  }
  ```

#### Customer Updating

- **URL:** `/api/customers/{customerId}`
- **Method:** PUT
- **Explanation:** Updates Existing Customer.
- **Request Body:**
  ```json
  {
    "name": "Jane Smith",
    "phone": "0987654321",
    "mail": "jane@example.com",
    "address": "456 Elm Street",
    "city": "Los Angeles"
  }
  ```

### Animal Operations

#### Create Animal

- **URL:** `/api/animals`
- **Method:** POST
- **Explanation:** Creates New Animal.
- **Request Body:**
  ```json
  {
    "name": "Buddy",
    "species": "Dog",
    "breed": "Golden Retriever",
    "gender": "Male",
    "colour": "Golden",
    "dateOfBirth": "2020-01-15",
    "customer": {
      "id": 1,
      "name": "John Doe",
      "phone": "1234567890",
      "mail": "john@example.com",
      "address": "123 Main Street",
      "city": "New York"
    }
  }
  ```

#### Updating Animal

- **URL:** `/api/animals/{animalId}`
- **Method:** PUT
- **Explanation:** Updates Existing Animal.
- **Request Body:**
  ```json
  {
    "id": 1,
    "name": "Buddy",
    "species": "Dog",
    "breed": "Labrador Retriever",
    "gender": "Male",
    "colour": "Black",
    "dateOfBirth": "2020-01-15",
    "customer": {
      "id": 1,
      "name": "John Doe",
      "phone": "1234567890",
      "mail": "john@example.com",
      "address": "123 Main Street",
      "city": "New York"
    }
  }
  ```


These examples include basic endpoints for customer and animal operations. You can customize them according to your actual endpoints.

## Author

Sinan Can Ozer