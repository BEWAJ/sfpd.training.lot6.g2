package be.sfpd.blog.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyCustomException  extends Exception implements ExceptionMapper<MyCustomException> {

	@Override
	public Response toResponse(MyCustomException e) {
		return Response
				.notModified()
				.status(Response.Status.NOT_MODIFIED)
				.build();
	}
}