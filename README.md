# iot-ingestion-service

A spring-boot based microservice that consumes the IoT data from kafka, processed & stores that data in a Postgres
database. The purpose of the service is to demonstrate a skeleton of a microservice for an enterprise application & not
to demonstrate IoT or actual data ingestion capabilities.

<h3>Overview</h3>
<ol>
    <li><b>Gradle</b> is used as a build tool</li>
    <li><b>Spring JPA</b> is used to interact with Postgres</li>
    <li><b>Liquibase</b> has been enabled for the microservice, <i>it ensures that new changes made to DB schema are run on the database by tracking the change logs as a part of ./src/main/resources/db.changelog-master.xml</i></li>
    <li>Unit testing is done using <b>Mockito & junit5</b></li>
    <li><b>Cucumber</b> has been used for integration-testing & has been set up as a separate Gradle task & a separate source-set</li>
</ol>

<h3>How to run the code?</h3>
<ol>
    <li>Install and set-up gradle version 7+</li>
    <li>Install and set-up JDK 17+, if you are using a lower JDK version just update the same in build.gradle. Minimum version of JDK required is Java8</li>
    <li>Import the code in your favourite IDE as an existing Gradle project & locate IoTIngestionApplication class & run it using local profile</li>
    <li>Before you run the code, change the DB configuration in application-local.yml</li>
</ol>


<h3>How to build the code using command line?</h3>
<ol>
    <li><b>gradle clean build</b> - this command will compile the code without running the test cases</li>
    <li><b>gradle test</b> - this command will execute the JUnits</li>
    <li><b>gradle integrationTest</b> - this command will execute the Cucumber Tests</li>
    <li><b>gradle build</b> - this command will compile the code, run the JUnits & cucumber tests</li>
</ol>