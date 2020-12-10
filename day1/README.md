# Day 1 : REST WebServices Workshop 

## Instructions for solving the exercises 

1. first create a test (a test example is provided in the client sample)
1. the test should fail 
1. add a basic implementation so the test can pass
1. run your server
1. the test should pass 
1. refactor your code and test again
1. commit the changes

## Resources 

* [A fake Online API](https://jsonplaceholder.typicode.com/)
* [RESTAssured Usage](https://github.com/rest-assured/rest-assured/wiki/Usage)
* [An article on TDD](http://www.agiledata.org/essays/tdd.html)
* [Resources Naming Best Practices](https://restfulapi.net/resource-naming/)

## Exercises 

The goal will be to create from scratch a REST API like [this](https://myfakeapi.com/)

### Analyse the content of the server

### Create your own module from the sample and Start the server

1. Duplicate the module `jaxrs.server.app.jersey.sample` into your own module  
1. Execute `mvn tomcat:run` to run the `server` project as a dynamic web application using an embedded Tomcat server

### Test the existing resources deployed by the server

Example : `http://localhost:8080/jaxrs.server.app.jersey.sample/webapi/myresource`

### Add JSON support

* Add the dependency in `pom.xml`  
```xml
<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
    <artifactId>jersey-media-json-binding</artifactId>
</dependency>
```

* In the class `ArticleResource` change the produces annotations to `@Produces(MediaType.APPLICATION_JSON)`

### Use POST

* Test a resource that handles the "creation" of "articles", the new resource will consume JSON 

### Add a Profile Resource

* Test an endpoint that handles the path "profiles"

### Use PUT 

* Test a resource that "updates" an "article"

### Use DELETE

* Test a resource that "deletes" an "article"

### Add Filtering and Pagination 

* Update the resource that fetches the articles with query parameters `offset`, `limit` and `year`
```java
@QueryParam("offset") int offset
```

### Refactoring 
