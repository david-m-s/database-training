# database-training


### Project source base:

Based on Spring initializr https://start.spring.io/ and picked the DB features: h2, mysql, flyway, jdbc and jpa.

### Quick maven tour

settings.xml
pom.xml
 parent
 dependency management
 plugins
 
### Spring boot quick overview
@SpringBootApplication, @SpringBootTest, @Autowired, @Component
application.properties, application-test.properties

### Exercise intro
show domain (User)
show migrations V1
show UserRepository#findAll
show UserRepository#save
show UserRepositoryTest

### Exercise JDBC
implement UserRepository#count and test
implement UserRepository#deleteById and test
implement UserRepository#findByUsernameStartingWith (projections) and test

### Migrations - flyway
Add email attribute

### Spring data JPA
Create crudRepository
