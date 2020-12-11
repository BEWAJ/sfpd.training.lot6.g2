package be.sfpd.blog.resource;

import be.sfpd.blog.service.ManagementService;

import javax.ws.rs.*;

@Path("management")
public class ManagementResource {

    final ManagementService managementService = new ManagementService();

    @GET
    @Path("/reset")
    public void resetDB() {
        managementService.resetDB();
    }

}



