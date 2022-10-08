package system.barManagement.service.resources;

import system.barManagement.service.JWTTokenNeeded;
import system.barManagement.service.controllers.WarehouseStocksController;
import system.barManagement.service.model.WarehouseStock;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@JWTTokenNeeded
@Path("/warehouse")
public class WarehouseResources {

    @Context
    private UriInfo uriInfo;
    SecurityContext securityContext;
    private static final WarehouseStocksController stocksController = new WarehouseStocksController();

    @GET //GET at http://localhost:XXXX/warehouse/
    @RolesAllowed("BAR_MANAGER, WAREHOUSE_KEEPER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllWarehouseItems() {
        List<WarehouseStock> stocks;
        stocks = stocksController.showAllWarehouseStocks();
        return Response.ok(stocks).build();
    }

    @GET //GET at http://localhost:XXXX/warehouse/2
    @RolesAllowed("BAR_MANAGER, WAREHOUSE_KEEPER")
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWarehouseStock(@PathParam("id") int id) {
        WarehouseStock stock = stocksController.showStock(id);
        if (stock == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid stock id.").build();
        } else {
            return Response.ok(stock).build();
        }
    }


    @POST //POST at http://localhost:XXXX/warehouse/
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWarehouseStock(WarehouseStock stock) {
        if (!stocksController.addWarehouseStock(stock)){
            String entity =  "Stock with id " + stock.getWarehouseStockId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + stock.getWarehouseStockId(); // url of the created stock
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/warehouse/
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateWarehouseStock(WarehouseStock stock) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (stocksController.updateWarehouseStock(stock)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid stock id.").build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/warehouse/3
    @RolesAllowed("WAREHOUSE_KEEPER")
    @Path("{id}")
    public Response deleteWarehouseStock(@PathParam("id") int id) {
        stocksController.deleteWarehouseStock(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }
}
