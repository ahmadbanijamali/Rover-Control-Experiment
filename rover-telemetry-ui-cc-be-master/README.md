Credits for initial implementations to [huigc001](https://github.com/huigc001)

# Telemetry-UI Backend with Hono C&C
The backend establish a connection to [Eclipse Hono](https://www.eclipse.org/hono/) for sending commands to according devices as well as consumes telemtry data from an InfluxDB to visualize them in the frontend.

## Running Telemetry-UI Backend

### Prerequisite
Tested on Ubuntu 18.04

#### Frameworks

Apache Maven (3.6.0)

```sh
sudo apt-get install maven
```

#### Configuration

To establish a connection to Eclipse Hono and InfluxDB, the `application.properties` at `/src/main/resources/` have to be configured with the according IPs and credentials.

### Starting the application
To run the application, enter the main folder and execute the following command:

```sh
mvn spring-boot:run
```

## What you need to know about the Application

* Controller are the REST-API implementations which use services to implement business logic
* the package payload contains the needed classes to use as JSON packet for communication to frontend and other software
* application.properties is used to configure hono and influxdb

### Implemented REST-API

### GET METHOD - `/telemetry`
This interface allows applications to get the telemetry data from an InfluxDB as JSON.

### POST METHOD - `/command-control`
This interface allows an application to move a rover by putting command and speed as JSON in a POST-Request.

## Current Limitations
* modular implementation for the REST-API so that the frontend can decide which of the rover's data it wants or which rover it wants to control
* other use cases for the third-party backend for example integrating the appstore backend to this backend to make access consistent
