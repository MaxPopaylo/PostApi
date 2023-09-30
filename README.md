## Launching databases

1. **Start Docker**: First, ensure Docker is running. You can start Docker by running:

   ```bash
   sudo service docker start
   
2. **Run DataBase**: The project includes a docker-compose.yaml file to run two databases. To start these databases, run the following command in the project directory:

   ```bash
   docker-compose -f docker-compose.yaml up -d

This will start the main database on port 5444 and an auxiliary database for integration tests on port 5445.

## Running Tests

* To run all tests, including unit tests and integration tests, execute the following Maven command in the project directory:

   ```bash
   mvn test

## Validation

Validation of incoming data is handled using Hibernate Validator. Additionally, a custom validation annotation called @ValidAge is used for age validation.

## Data Update Endpoints

All fields, multiple fields and single field can be updated at once through the "Update" endpoint, **but** I thought that this might violate the SOLID rules, so I created separate endpoints to update each field.
