# go-health-lssbt-be-service
## SWE Home Assignement in Java

### Configuration
Define the local storage type by setting the `local-storage.type` configuration variable in `application-local.yaml`.
Supported options are `db` and `txt`.

### Database Setup
If using the `db` local storage type, ensure PostgreSQL is running on your local machine. 
You can start PostgreSQL using Docker with the following command:
```sh
docker-compose up -d
```

### Running the Application
To run the application, use the following command:
```sh
./gradlew run --console=plain
```

### Technologies and Tools
- `JOOQ` was chosen as an ORM.
- `spotless` was used for code formatting.
- 

### Testing
A helper for local storage in tests is implemented only for database interactions, 
so tests will be executed only if `local-storage.type` is set to `db`.

To enable tests to run with `local-storage.type` = `txt` as well, please,
implement the three methods in the `IssueTxtTestsHelper` class and remove the `EnabledIf` annotation from the `IssueTestsHelper` class.

### Must-Have TODOs (if I had time)
- Implement support for `csv`.
- Use `sonarQube` for static code analysis.
- Use `jacoco` to measure test coverage.
- Add logging.
- Implement custom error codes, errors and exceptions.
- Write tests for the persistence and ui layers.
- CI/CD pipeline
