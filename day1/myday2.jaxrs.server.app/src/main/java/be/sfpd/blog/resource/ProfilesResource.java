package be.sfpd.blog.resource;

import be.sfpd.blog.model.Profile;
import be.sfpd.blog.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("profiles")
public class ProfilesResource {

    final ProfileService profileService = new ProfileService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getAllProfiles() {
        return profileService.getProfiles();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Profile createProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfileByName(@PathParam("name") String name) {
        return profileService.getProfileByName(name);
    }
}
