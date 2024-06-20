# go-health-lssbt-be-service
## SWE Home Assignement in Java

### define the local storage you want to use by `local-storage.type` configuration variable in `application.yaml`
### supported options: `db`, `txt`
##
### before starting application with `db` local storage type, you should start postgresql on your local machine
### you can do it by writing `docker-compose up -d` to console
##
### to run the application write `./gradlew run --console=plain` into your console.
##
### `JOOQ` was chosen as an `ORM`
### `spotless` was used for code formatting
##
### helper for local storage in tests was written only for interaction with database
### so the tests will be executed only and only if `local-storage.type` is equal to `db`
### to do tests possible for `local-storage.type` = `txt`, all you need is to implement three methods
### in `IssueTxtTestsHelper` java class, as well as to remove `EnabledIf` annotation from `IssueTestsHelper` class
##
### Must have TODOs:
### - implement support for `csv`
### - `sonarQube` was used for static code analysis
### - `jacoco` was used for measure the coverage of tests
### - add logging
### - implement custom error codes / errors / exceptions
### - tests to be done for repository only as well as for controller (feature spec) only
