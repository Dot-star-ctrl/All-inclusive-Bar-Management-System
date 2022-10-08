package APITests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;
import system.barManagement.service.model.WarehouseStock;
import system.barManagement.service.repository.MyUtil;
import system.barManagement.service.resources.WarehouseResources;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WarehouseResourcesIntegrationTest extends JerseyTest {

    @Override
    protected Application configure(){
        return new ResourceConfig(WarehouseResources.class);
    }


    @Test
    public void Test1CreateWarehouseStock() {
        //arrange
        WarehouseStock stock = new WarehouseStock(2,13);

        //act
        Response response = target("/warehouse").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(stock, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    public void Test2GetWarehouseStocks() {
        //arrange & act
        final String json = target("/warehouse").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"[{\"itemId\":2,\"quantity\":13,\"warehouseStockId\":2}]");
    }

    @Test
    public void Test3GetWarehouseStockById() {
        //arrange & act
        final String json = target("/warehouse/2").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"{\"itemId\":2,\"quantity\":13,\"warehouseStockId\":2}");
    }

    @Test
    public void Test4UpdateWarehouseStocks() {
        //arrange
        WarehouseStock stock = target("/warehouse/2").request().get(WarehouseStock.class);
        stock.setItemId('4');

        //act
        Response response = target("/warehouse/2").request()
                .put(Entity.entity(stock, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }


    @Test
    public void Test5DeleteBars() {
        //arrange & act
        Response response = target("/warehouse/2").request().delete();

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }
}
