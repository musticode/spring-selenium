
### Spring boot selenium
#### Spring boot summary
@Component - for automatic configuration <br>
@Configuration & @Bean for manual configuration

@Autowired for spring beans <br>
@Value - from application.properties, env variables

>Bean has a lifecycle
>> @PostConstruct <br>
>> @PreDestroy

### Maven Dependencies

```

Dear All,

I would be using older versions of Selenium in the following lectures (It was latest at the time of recording). But please use the following latest versions of dependencies.

To be added under properties section

<selenium.version>4.2.1</selenium.version>
<webdrivermanager.version>5.2.0</webdrivermanager.version>
<testng.version>7.6.0</testng.version>


To be added under dependencies section after spring-boot-starter

<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>${selenium.version}</version>
</dependency>
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>${webdrivermanager.version}</version>
</dependency>
<!-- testng users only -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>${testng.version}</version>
    <scope>test</scope>
</dependency>	


To be added under plugin section

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
</plugin>
```

### WebDriverWait Update
Note: WebDriverWait Update

Older Versions:

```
new WebDriverWait(driver, this.timeout);
```
New Versions:
```
new WebDriverWait(driver, Duration.ofSeconds(this.timeout));
```

### Spring profiles
in test configuration page --> spring.profiles.active=qa  or stg

run in terminal
```
mvn clean test -Dspring.profiles.active=qa
```


## Running Selenium Grid

Running Selenium Grid
Docker Users:



Save this content in a file called - docker-compose.yml
```
version: "3"
services:
  hub:
    image: selenium/hub:3.14
    ports:
      - "4444:4444"
  chrome:
    image: selenium/node-chrome:3.14      
    depends_on:
      - hub
    environment:
      - HUB_HOST=hub
  firefox:
    image: selenium/node-firefox:3.14
    shm_size: '1gb'   
    depends_on:
      - hub
    environment:
      - HUB_HOST=hub
```
Issue **docker-compose up** command to bring up selenium grid & **docker-compose down** for bringing it down.

Non-docker Users:

Download this jar and move it to a separate directory called 'grid'.

Open 2 terminal/command line windows

Navigate to the 'gird' directory.

Issue this command in the first terminal/command line window.

**java -jar selenium-server-standalone-3.141.59.jar -role hub**

You would see a message - ' Selenium Grid hub is up and running' in few seconds

Issue this command in the second terminal / command line. Update the chrome driver path before running the command.

java -Dwebdriver.chrome.driver=/path/of/the/chromedriver -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register

You should see this message - 'The node is registered to the hub and ready to use'

You can access your grid in your browser.

http://localhost:4444/grid/console

Please do note that WebDriver will be using http://localhost:4444/wd/hub as the grid URL.

### Parallel Test Execution via Docker Selenium Hub

link: <br>
https://medium.com/@iamfaisalkhatri/parallel-execution-of-tests-using-selenium-grid-4-with-docker-compose-2dc243f4fe8b


### TestNG Suite Xml File

```
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="spring-boot" parallel="tests">
    <test name="test-1">
        <classes>
            <class name="com.udemy.spring.springselenium.googletest.Google1Test" />
        </classes>
    </test>
    <test name="test-2">
        <classes>
            <class name="com.udemy.spring.springselenium.googletest.Google2Test" />
        </classes>
    </test>
</suite>	
```
### JUnit

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.19.1</version>
    <configuration>
        <includes>
            <include>com.udemy.spring.springselenium.googletest.*</include>
        </includes>
        <parallel>classes</parallel>
        <threadCountClasses>2</threadCountClasses>
    </configuration>
</plugin>
```
```
@SpringBootTest
@RunWith(SpringRunner.class) // import org.junit.runner.RunWith;
public class Google1Test  {
 
@Test // should be from import org.junit.Test;
 
}
```

### Scope
#### Singleton
- Only one instance of the bean
- Shared with all the objects
- Shared with all the threads
- Good for reporting/logging
- Avoid mutating instances variables
- Your responsibility to keep it thread safe
- Configured one time

#### Prototype
- Fresh instance for all
- Note: @PreDestroy is not invoked
- Configured every time with latest beans available


#### @Component Beans using Application Context

```
@Autowired
private ApplicationContext ctx;

...
...

Car car1 = this.ctx.getBean(Accord.class); // to get accord bean
Car car2 = this.ctx.getBean(Civic.class);  // to get civic bean
```
Suggestion:
Notes: <br>
> Bean Scope
> - Singleton
> - Prototype
> - ThreadScope -custom scope

> Application Context
> - to get bean ourselves
> - getBean("method", WebDriver.class)

#### Accesing Resources
```
@Value("classpath:features/data/csv")
private Resource classpathResource;

@Value("www.google.com")
private Resource urlResource;

@Value("file:/home/udemy/data/csv")
private Resource fileResource;
```

### Test Data
#### DataProvider
```
@Test(dataProvider = "getData")
public void getDataProvider(String data){

}

@DataProvider
public Object[][] getData() throws IOException {
    return Files.readAllLines(resource.getFile().toPath())
            .stream()
            .map(s -> s.split(","))
            .toArray(Object[][]::new);
}
```

#### Note: Entity Class Name Change

Older versions (Spring version < 2.5):
```
DROP TABLE IF EXISTS user;
CREATE TABLE user AS SELECT * FROM CSVREAD('classpath:tables/user_visa.csv');
```
New Versions (Spring version > 2.5):
```
DROP TABLE IF EXISTS customer;
CREATE TABLE customer AS SELECT * FROM CSVREAD('classpath:tables/user_visa.csv');
```



Additional Property


There seems to be a recent change in Spring Boot. Check your Spring Version. if it is >= 2.5, then add this property in your application.properties to make the following lectures work.
```
spring.jpa.defer-datasource-initialization=true
```
