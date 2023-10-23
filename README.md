## Launching databases

1. **Start Docker**: First, ensure Docker is running. You can start Docker by running:

   ```bash
   sudo service docker start
   
2. **Run DataBase**: The project includes a docker-compose.yaml file to run two databases. To start these databases, run the following command in the project directory:

   ```bash
   docker-compose -f docker-compose.yaml up -d

This will start the main database on port 5444 and an auxiliary database for integration tests on port 5445.
