package com.keycloak.remoteuserstorageprovider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface RemoteUserApiService {

    @GET
    @Path("/{userName}")
    User getUserDetails(@PathParam("userName") String userName);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}/verify-password")
    VerifyPasswordResponse verifyPassword(@PathParam("userName") String userName, String password);
}
