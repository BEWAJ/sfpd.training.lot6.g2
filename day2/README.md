# Day 2 : REST WebServices Workshop

### SubResource

* Test a resource that handles the "creation" of "comments" per "article", the new resource will consume JSON

### Status Codes 

* Instead of using POJO such as `Article` or `Comment` as endpoint response type , use `javax.ws.rs.core.Response`
* With `javax.ws.rs.core.Response`, set custom Status Codes
* Verify the returned status codes

### Exception Handling 

* Create your custom exception 
```java 
public class MyCustomDatabaseException extends RuntimeException {
}
```
* Test a scenario where that type of exception can occur

### HATEOAS 

* Test a resource that returns your web service definition in the HATEOAS format
```json 

```