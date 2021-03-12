[![codecov](https://codecov.io/gh/JulieTetrault/property-rental-api/branch/main/graph/badge.svg?token=VSTER44M37)](https://codecov.io/gh/JulieTetrault/property-rental-api)

# property-rental-api

🏠 A simple REST API to find properties for rent

## Getting Started

### Install the Dependencies

This project uses [Maven](https://maven.apache.org/). First of all, make sure you have it globally installed by following the instructions [here](https://maven.apache.org/install.html).

Then, go to the project's root and install its dependencies:

```{bash}
mvn clean install
```

To start the app at [http://localhost:8080](http://localhost:8080), run the following command:

```{bash}
mvn exec:java -pl app
```

### Run the Tests

We use [JUnit](https://junit.org/junit5/) as our testing framework. To run the `unit tests`, execute the following command:

```{bash}
mvn test
```

We use [Jacoco](https://www.jacoco.org/jacoco/) to build the test coverage reports. You can view those reports at [Codecov](https://app.codecov.io/gh/JulieTetrault/property-rental-api).

### Run the Linter

We use the [Checkstyle](https://checkstyle.org/) plugin to check the validity of the `Java` files. You can run the linter with the following command:

```{bash}
mvn checkstyle:checkstyle
```

### Routes

#### GET /rentals

The supported query parameters are:

- `min_nb_beds`: filters all rentals with a number of beds above given minimum
- `postalcode`: filters all rentals with a postal code matching given pattern
- `min_price`: filters all rentals with a price above given minimum
- `max_price`: filters all rentals with a price under given minimum

It is possible to apply multiple filters at the same time:

`GET /beds?min_nb_beds=2&postalcode=G3A__4&min_price=100&max_price=300`

#### GET /rentals/{id}
