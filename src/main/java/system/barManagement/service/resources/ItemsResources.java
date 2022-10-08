package system.barManagement.service.resources;


import system.barManagement.service.JWTTokenNeeded;
import system.barManagement.service.controllers.ItemsController;
import system.barManagement.service.model.Item;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@JWTTokenNeeded
@Path("/items")
public class ItemsResources {
    @Context
    private UriInfo uriInfo;
    SecurityContext securityContext;
    private static final ItemsController itemsController = new ItemsController();

    @GET //GET at http://localhost:XXXX/items/
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items;
        items = itemsController.showAllItems();
        return Response.ok(items).build();
    }

    @GET //GET at http://localhost:XXXX/items/2
    @PermitAll
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        Item item = itemsController.showItem(id);
        if (item == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid item id.").build();
        } else {
            return Response.ok(item).build();
        }
    }

    @POST //POST at http://localhost:XXXX/item/
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {
        if (!itemsController.addItem(item)){
            String entity =  "Employee with id " + item.getItemId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + item.getItemId(); // url of the created item
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/items/
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateItem(Item item) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (itemsController.updateItem(item)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid item id.").build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/items/3
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Path("{id}")
    public Response deleteItem(@PathParam("id") int id) {
        itemsController.deleteItem(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }
}
