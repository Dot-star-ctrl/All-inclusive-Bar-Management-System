package APITests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;
import system.barManagement.service.model.Bar;
import system.barManagement.service.repository.MyUtil;
import system.barManagement.service.resources.BarsResources;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BarsResourceIntegrationTest extends JerseyTest {

    @Override
    protected Application configure(){
        return new ResourceConfig(BarsResources.class);
    }

    @Test
    public void Test1CreateBars() {
        //arrange
        Bar bar = new Bar("Lobby bar");

        //act
        Response response = target("/bars").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(bar, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    public void Test2GetBars() {
        //arrange & act
        final String json = target("/bars").request().get(String.class);
        //assert
        Assertions.assertEquals(json,"[{\"assignedBartender\":0,\"barId\":2,\"barName\":\"Lobby bar\"}]");
    }

    @Test
    public void Test3GetBarById() {
        //arrange & act
        final String json = target("/bars/2").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"{\"assignedBartender\":0,\"barId\":2,\"barName\":\"Lobby bar\"}");
    }

    @Test
    public void Test4UpdateBars() {
        //arrange
        Bar bar = target("/bars/2").request().get(Bar.class);
        bar.setBarName("Pool Bar");

        //act
        Response response = target("/bars/2").request()
                .put(Entity.entity(bar, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }


    @Test
    public void Test5DeleteBars() {
        //arrange & act
        Response response = target("/bars/2").request().delete();

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }
}
