package system.barManagement.service.resources;

import system.barManagement.service.JWTTokenNeeded;
import system.barManagement.service.controllers.BarsController;
import system.barManagement.service.model.Bar;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@JWTTokenNeeded
@Path("/bars")
public class BarsResources {

    @Context
    private UriInfo uriInfo;
    private static final BarsController barsController = new BarsController();

    @GET //GET at http://localhost:XXXX/bars/
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBars() {
        List<Bar> bars = barsController.showAllBars();
        return Response.ok(bars).build();
    }

    @GET //GET at http://localhost:XXXX/bars/2
    @PermitAll
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBar(@PathParam("id") int id) {
        Bar bar = barsController.showBar(id);
        if (bar == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid bar id.").build();
        } else {
            return Response.ok(bar).build();
        }
    }

    @POST //POST at http://localhost:XXXX/bars/
    @RolesAllowed("BAR_MANAGER")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBar(Bar bar) {
        if (!barsController.addBar(bar)){
            String entity =  "Bar with id " + bar.getBarId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + bar.getBarId();
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/bars/2
    @RolesAllowed("BAR_MANAGER")
    @Path("{id}")
    public Response deleteBar(@PathParam("id") int id) {
        barsController.deleteBar(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }

    @PUT //PUT at http://localhost:XXXX/bars/4
    @RolesAllowed("BAR_MANAGER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateBar(Bar bar) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (barsController.updateBar(bar)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid bar id.").build();
        }
    }
}
