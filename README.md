Java version: 1.8.0_121  
To assemble the application: In the root project folder run maven command 'mvn clean package'  

Available profiles:

    1. 'embedded' 

Uses Embedded Tomcat and and embedded database H2.<br>
To access db you should add '/h2-console/' to the application URL.<br>

**JDBC URL: jdbc:h2:mem:testdb<br>
User Name: sa<br>
Password: <br>**

    2.  'main'

Uses Embedded Tomcat and and PostgreSQL database.<br>
Before application run you should create 'spring_boot' database.<br>

**spring.datasource.url=jdbc:postgresql://host:port/spring_boot<br>
spring.datasource.username=postgres<br>
spring.datasource.password=admin<br>**


    3. 'jndi' 

Uses External Tomcat and and PostgreSQL database.<br>


* Before application run you should create 'spring_boot' database.<br>
    
    **spring.datasource.url=jdbc:postgresql://host:port/spring_boot<br>
    spring.datasource.username=postgres<br>
    spring.datasource.password=admin<br>**


* ***In the pom file you should add scope 'provided' for next dependencies:***<br>
    
    `<groupId>org.apache.tomcat.embed</groupId>` <br>
    `<groupId>org.eclipse.jdt.core.compiler</groupId>` <br>


* You should modify 'server.xml' and 'context.xml' Tomcat configuration files: <br>
	'<Resource name="jdbc/SpringBootDB"<br>
 			  global="jdbc/SpringBootDB" <br>
 			  auth="Container" <br>
 			  type="javax.sql.DataSource"<br> 
 			  driverClassName="org.postgresql.Driver"<br> 
 			  url="jdbc:postgresql://host:port/spring_boot"<br> 
 			  username="postgres"
 			  password="admin"	
 			  maxActive="100"
 			  maxIdle="20"
 			  minIdle="5"
 			  maxWait="10000"/>
 			  
 	<ResourceLink name="jdbc/SpringBootLocalDB"<br>
                                global="jdbc/SpringBootDB"<br>
                                auth="Container"<br>
                                type="javax.sql.DataSource" />'

To deploy and run the application using embedded tomcat: In the root folder run maven command 'mvn spring-boot:run'  
**Use http://localhost:8000/shop** to achieve the app  

User | Password 
-------------------
user | user
------------------- 
dev  | dev 
-------------------
