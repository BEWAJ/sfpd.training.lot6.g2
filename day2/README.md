# Day 2 : REST WebServices Workshop

### SubResource

* Test a resource that handles the "creation" of "comments" per "article", the new resource will consume JSON

### Status Codes 

* Instead of using POJO such as `Article` or `Comment` as endpoint response type , use `javax.ws.rs.core.Response`
* With `javax.ws.rs.core.Response`, set custom Status Codes
* Verify the returned status codes

### Exception Handling 

* Create your custom exception (example below)
```java 
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
public class MyCustomDatabaseException extends Exception implements
                ExceptionMapper<MyCustomDatabaseException>  {
}
```
* Test scenarios where an exception can occur

### HATEOAS 

* Test a resource that returns your web service definition in the HATEOAS format
```json 
{
"author": "guy",
"id": 1,
"message": "Hello SFPD.",
"links": [
	{
		"link": "http://localhost:8080/sfpd-blog/webapi/articles/1",
"rel": "self"
},
{
		"link": "http://localhost:8080/sfpd-blog/webapi/articles/1/comments",
"rel": "comments"
}

]
}
```
### OpenAPI

* (Live Demo) Generate the service definition of teh REST API with OpenAPI specification