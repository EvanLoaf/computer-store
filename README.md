# Computer Store

Computer Store is a secure Web/REST application with multi-module architecture 
designed to serve the needs of PC retail.

## Installation

### Requirements
* Java 8
* MySQL Server 8
* Apache Tomcat 9
* Apache Maven 3.8

Computer Store has profiles for development/production purposes. Make sure to adjust 
profiles and `*.properties` files before deploying.

Application can be packaged with Maven `mvn clean install -DskipTests=true` and 
deployed to Apache Tomcat Server.

## Technologies

The list of frameworks, libraries, tools, etc. used in 
development of the Computer Store application includes but is not limited to:
* Java 8 Core, EE
* Spring (Core, ORM, Web MVC, Security, REST API)
* Hibernate, HQL, EhCache, HikariCP
* MySQL Server, SQL
* Liquibase
* JSP, JSTL, EL
* Log4j2, SLF4J
* Git, GitHub
* Jackson Databind, JSON
* JaXB, XML, XSD
* Patterns
* Regex
* Internationalization (.properties)
* Apache Maven
* Apache Tomcat

## Scope of functionality

##### All users:
* Registration
* Login
* Logout

##### Security Admin:
* Users: show all, change role, password, disable, soft delete

##### Sales Admin:
* News: show, create, update, soft delete
* Comments: show, soft delete
* Items: show, create, soft delete, upload with XML
* Orders: show all, change status
* Feedback: show all, delete
* Discounts: set for users, set for items

##### API Admin:
* Items: show, create, update, soft delete
* Business Cards: show all, soft delete

##### User:
* News: show, create comments
* Items: show
* Orders: show, create, soft delete
* Feedback: create
* Profile: show, update
* Business cards: show, update, delete

## Access

All passwords are **encrypted** and access is authorized, 
therefore I'm providing mock profiles for **test** and **development** purposes only.

Security Admin: *security@pc.com* *security_root*

API Admin: *api@pc.com* *api_root*

Sales Admin: *sales@pc.com* *sales_root*

User: *user@pc.com* *user_root*

## Contributions
Pull requests are welcome. For major changes, please open an issue first 
to discuss possible improvements and/or extension.

For any inquiries: evanloafakahaitao@gmail.com

## License
[MIT](https://choosealicense.com/licenses/mit/)