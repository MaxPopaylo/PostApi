## Launching databases & front-end

1. **Start Docker**: First, ensure Docker is running. You can start Docker by running:

   ```bash
   sudo service docker start
   
2. **Run DataBase**: The project includes a docker-compose.yaml file to run two databases. To start these databases, run the following command in the project directory:

   ```bash
   docker-compose -f docker-compose.yaml up -d

This will start the main database on port 5444 and an auxiliary database for integration tests on port 5445.

## Diagram
[Draw.io](https://drive.google.com/file/d/148QH7bi-Ke9C6jHQSch2IViiRtJCL1yj/view?usp=sharing)

## Tests
[Postman](https://elements.getpostman.com/redirect?entityId=28421538-87dded9a-f8bb-4628-84d9-e6ba5a442720&entityType=collection)
