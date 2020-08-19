Requirements
============

1. Mysql Server 5.6
2. gradle
3. Angular Cli
4. Java 1.8

Start Apps
==========

1. Start MySQL Server 5.6

2. Start Discovery Service (SpringBoot/Eureka - Port 8761)
    a. Open a terminal
    b. Run [Project]/interbanking-discovery-service/gradlew bootRun 
    c. Wait to message "Service alredy started

3. Start Gateway Service (SpringBoot/Zuul - Port 8765)
    a. Open a terminal
    b. Run [Project]/interbanking-gateway-service/gradlew bootRun 
    c. Wait to message "Service alredy started

4. Start Authorization JWT Backend (SpringBoot - Port 8000)
    a. Open a terminal
    b. Run [Project]/interbanking-auth/gradlew bootRun 

5. Start StockOption Backend (SpringBoot - Port 8001/8002)
    a. Open a terminal
    b. Run [Project]/interbanking-stockoption/gradlew bootRun 
	c. Open a terminal
    d. Run [Project]/interbanking-stockoption/gradlew bootRun --args='--server.port=8002'

6. Start Angular App (Angular 10)
    a. Open a terminal
    b. Run [Project]/interbanking-client/ng serve 

7. Execute SQL on ib_auth schemma
    INSERT INTO roles(name) VALUES('ROLE_USER');
    INSERT INTO roles(name) VALUES('ROLE_ADMIN');

8. Access
    StockOption Simulator: http://localhost:4200/home

    Register yourself and Access

9. Execute Simulation

    a. Go to StockOption menu
    b. Upload file
    c. Press Simulation button
    d. Set the configurations for your simulation
	e. Press Start Simulation
