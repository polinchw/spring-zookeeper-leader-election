# spring-zookeeper-leader-election

## Description
Example of Leader election pattern with springboot and zookeeper

## Dependencies

This app is dependent on a Zookeeper a running.  To point this app
to a Zookeeper set the following in the Spring application.properties file:

```
spring.cloud.zookeeper.connect-string=172.20.0.3:30769
```

## Build the app with Maven

```
mvn clean install
```

## Run the app

```
java -jar target/spring-zookeeper-leader-election-0.0.1-SNAPSHOT.jar
```

or

```
mvn spring-boot:run 
```

## API

You can now access the app at the following url.  You should received a 'yes' in the response.

```
http://localhost:8080  
``` 