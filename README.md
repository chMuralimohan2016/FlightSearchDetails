## FlightSearchDetails
The Flight search Details finds flights based on source, destination. Additionally, it sorts the flight details based on duration or price in the ascending/descending order depending on input.

#### Project Description
This project finds the list of flights based on passing the query parameters to endpoint like flight/flights, this is available on port 8081.

Below are the request parameters: * Origin: Required * Destination: Required * priceSort (ASCE / DESC): Optional * durationSort(ASCE / DESC) : Optional

#### Prerequisite To Run
For building and running the application you need: * [JDK 11] * [Maven 3.x]

#### How To Run Locally
This spring boot application can be run directly from STS/any IDE which supports Spring Boot 2.7.10.

* Clone this repository

* Make sure you are using JDK 11 and Maven 3.x

* You can build the project and run the tests by running
  			
				 		 mvn clean install       			    

* Once successfully built, you can run using Run as Spring boot Application directly from IDE

This application can run without an IDE as well. Only make sure maven is installed and correctly configured in system environment variable. Navigate to the root of the project via command line and execute the below command

                      mvn spring-boot:run	 		 