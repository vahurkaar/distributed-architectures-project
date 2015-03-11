# distributed-architectures-project

This project was part of my school assignment in Tallinn University of Technology for the course of Distributed Systems.
My assignment was to develop a distributed system, which consists of 4 different web applications and servers. We were required to use different programming languages and frameworks to finish the task. 
The domain model was given at the start of the assignment. It consisted of 2 logical groups: customers and contracts.
This current solution was made with Java and Groovy on Grails. During this project I had my first experience with the Groovy programming language and Grails framework. 

The 4 webapplications are:

1. Core application
2. Customer mgmt webapp
3. Contracts mgmt webapp
4. Central Authentication Service (CAS)

Each webapplication of the project is described below. The final deployed solution used HTTPS to communicate with the CAS, customers and contracts webapps.

### 1. Core application
The core web application was required to contain the main business logic of the entire system. All the communication with the core is made through the SOAP protocol. The core web application is implemented with Spring Web Services. I used JAXB as the method to convert SOAP messages from XML to Java and vice-versa. I separated the JAXB realization as a separate module, which can be imported to other applications as well. The core had to be reliable, so I decided to thoroughly test it with the aid of jUnit, Mockito and HSQL DB. 

### 2. Customer mgmt webapp
The customer management webapplication contains a basic search form for finding customers and making CRUD operatsion on them. The application is implemented with Groovy on Grails. This was my first experience with Groovy and Grails. I used Groovy's WsLite plugin and closure statements to impelement the SOAP request building and communication with the Core application.

### 3. Contracts mgmt webapp
The contracts mgmt webapp contains a simple search form for finding contracts by different parameters and making CRUD operations on them. Some validation rules are applied, when saving or updating contracts, the same as in the customers webapp.
This application was implemented as a regular Spring MVC application.

### 4. Central Authentication Service
This webapplication is the first entrypoint to the system. Both the customer and contracts webapplications first check whether the user has been authenticated through the CAS. The CAS solution also had Single-Signout functionality. It has been implemented with the opensource solution [Jasig CAS](http://jasig.github.io/cas/4.0.x/index.html). I used the Maven overlay and made customizations for my solution.

The assignment was completed in November 2014. This project demonstrates my knowledge on how to implement SOAP web services with Spring Framework, some basic knowledge on Groovy on Grails and how to configure a CAS solution using Jasig CAS.
