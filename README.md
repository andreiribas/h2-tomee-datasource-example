h2-tomee-datasource-example
=====================

Example application showing the use of H2 database in Apache Tomee 1.7.2.

To create the project,

just run the command below:

mvn eclipse:eclipse

Or import it into eclipse using Import -> Existing Maven Projects.

It will create the eclipse project.

To build the project, run 

mvn clean install

To deploy it in Tomee:

1) Create a new H2 database and connect to it.

2) Add the h2.jar in the lib/ directory in Tomme installation folder,

3) Change the H2 database path in the WEB-INF/resources.xml to your environment.

4) Compile the project.

5) Start Tomee in the 8080 port.

6) Deploy the application by running mvn tomme:deploy, the pom.xml already has the tomme-maven-plugin.

7) Access the application in http://localhost:8080/h2-tomee-datasource-example/

The full explanation is in my blog:
https://andreiribas.wordpress.com/2015/06/15/create-and-use-datasources-in-apache-tomee-1-7-2-with-h2-database/