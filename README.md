# Using Thymeleaf in Spring Boot
Thymeleaf is a Java template engine for processing and creating HTML, XML, JavaScript, CSS and text. It is a better alternative to JSP and other template engines because it provides a way to create templates using natural templates: HTML, CSS, and JavaScript. 
It is a server-side template engine that can process templates and render them to HTML.


Integrating Thymeleaf With Spring

> Note: 
> - Spring 4 project, we have to use the thymeleaf-spring4 and thymeleaf dependency. 
> - For Spring 5, we have to use the thymeleaf-spring5 and thymeleaf dependency.
> - For Spring Boot, we can use the spring-boot-starter-thymeleaf dependency, which will all the required dependencies for Thymeleaf.
> - This example is based on the Spring Boot project. If you don't know how to create a Spring Boot project, please refer to this link.
> - 

> https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html


Thymeleaf in Spring Boot
Spring Boot provides auto-configuration for Thymeleaf by adding the spring-boot-starter-thymeleaf dependency:



No explicit configuration is necessary. By default, HTML files should be placed in the resources/templates location.