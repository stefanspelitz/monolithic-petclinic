# Breaking The Monolith

The purpose of this fork is to be a monolithic mess. 
You can use it to break and extract microservices.
As the PetClinic is continuously deployed you need to make small changes that preserve the behaviour.
The tasks involve refactoring, TDD, branch by abstraction and introducing an event bus.

Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/).

Find an Overview in this [PDF](https://github.com/gregorriegler/monolithic-petclinic/blob/master/Monolithic%20Pet%20Clinic.pdf)

## Working with the Monolithic Petclinic

The following items should be installed in your system:
* Java 8 or newer.
* git

1) **Clone the repository**
    ```
    git clone https://github.com/gregorriegler/monolithic-petclinic.git
    ```
2) **Import into Eclipse or STS**
    ```
    File -> Import -> Maven -> Existing Maven project
    ```
3) **Import into IntelliJ IDEA**

    In the main menu, choose `File -> Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.

4) **Run it on the CLI**

    You can run it with the spring boot maven plugin like this:
    ```
    cd monolith
    ../mvnw spring-boot:run
    ```

5) **Navigate to the Petclinic**

    Visit [http://localhost:8080](http://localhost:8080) in your browser.

## Database configuration

Petclinic uses an in-memory database (HSQLDB) which gets populated at startup with data.

## License

[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](LICENSE)  

## Notes

This repository is used for the workshop 'Refactoring a Monolith to Microservices'

Simona test