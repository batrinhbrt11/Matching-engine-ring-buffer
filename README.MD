# Setup

Nodejs 20
Maven 3.8.6
Java 17
Mysql
Yarn 1.22.22

# Set up db

# Run source code

Rewrite database config in application-local.yml

```
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://<host>/<db_name>?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true
    username: <username>
    password: <password>
```

## BE

```
mvn clean install
mvn spring-boot:run
```

BE will be running on port 8081

## FE

```
yarn install
yarn dev
```

FE will be running on port 3000

### Login

```
GO TO http://localhost:3000/authentication
```

### Register

```
GO TO http://localhost:3000/authentication/sign-up
```
