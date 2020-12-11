package be.sfpd.blog;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource
 */
@Path("status")
public class StatusResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
	@PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String isUp() {
        return "My REST WebService is UP";
    }
}
