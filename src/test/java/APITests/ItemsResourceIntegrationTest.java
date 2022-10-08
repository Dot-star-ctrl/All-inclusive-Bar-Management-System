package APITests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;
import system.barManagement.service.model.Item;
import system.barManagement.service.repository.MyUtil;
import system.barManagement.service.resources.ItemsResources;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemsResourceIntegrationTest extends JerseyTest {

    @Override
    protected Application configure(){
        return new ResourceConfig(ItemsResources.class);
    }

    @Test
    public void Test1CreateItems() {
        //arrange
        Item item = new Item("orange juice",1.80);

        //act
        Response response = target("/items").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(item, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    public void Test2GetItems() {
        //arrange & act
        final String json = target("/items").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"[{\"itemId\":2,\"itemName\":\"orange juice\",\"price\":1.8}]");
    }

    @Test
    public void Test3GetItemById() {
        //arrange & act
        final String json = target("/items/2").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"{\"itemId\":2,\"itemName\":\"orange juice\",\"price\":1.8}");
    }

    @Test
    public void Test4UpdateItems() {
        //arrange
        Item item = target("/items/2").request().get(Item.class);
        item.setItemName("apple juice");

        //act
        Response response = target("/items/2").request()
                .put(Entity.entity(item, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }


    @Test
    public void Test5DeleteEmployees() {
        //arrange & act
        Response response = target("/items/2").request().delete();

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }
}
