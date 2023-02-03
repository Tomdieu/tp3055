# tp3055

Made with ReactJs and Spring Boot

## Frontend

Run Frontend:

```
cd frontend
```

Install The dependency

```
npm install
```

Start Development Server with

```
npm run dev
```

## Backend

Run backend

```
cd backend
```

Create a mysql database with name `general_colis` as in the application properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/general_colis?useSSL=false&TimeZone=UTC
spring.datasource.username=root
spring.datasource.password=
```

Run

```
mvn spring-boot:run
```

This will install the dependency if they are not install
