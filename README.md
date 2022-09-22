# spring-zookeeper-leader-election

## Description
Example of leader election pattern with Springboot and Zookeeper.

## Dependencies

This app is dependent on a Zookeeper server running.  To point this app
to Zookeeper set the following in the Spring application.properties file:

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

You can now access the app at the following url.  You should received a 'yes' in the response.  'yes' means the running app is the leader.  'no'
means it is not the leader.

```
http://localhost:8080  
``` 