JSONPlaceholderClient
==========

## Operations
### Build Docker Image
* ./gradlew jibDockerBuild

Note: Make sure that the port 8080 is being exposed from Docker.

### Run in console
* ./gradlew bootRun

### Create Jar
* ./gradlew bootJar

### Run Jar
* java -jar build/libs/json-placeholder-client-0.0.1.jar 5

## Notes
* You can use local gradle binary ("gradle") instead of bundled gradle wrapper ("gradlew")
* On Windows use gradle.sh or gradlew.sh respectively 

## Known issues
* JavaDocs missing on many places
* Tests are missing
* Spring security is not used
