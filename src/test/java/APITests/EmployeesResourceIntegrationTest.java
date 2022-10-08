package APITests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runners.MethodSorters;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.MyUtil;
import system.barManagement.service.resources.EmployeesResources;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeesResourceIntegrationTest extends JerseyTest {

    @Override
    protected Application configure(){
        return new ResourceConfig(EmployeesResources.class);
    }

    @Test
    public void Test1CreateEmployees() {
        //arrange
        Employee employee = new Employee("Jim", "pass123", AccountType.BAR_MANAGER);

        //act
        Response response = target("/employees").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    public void Test2GetEmployees() {
        //arrange & act
        final String json = target("/employees").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"[{\"accountType\":\"BAR_MANAGER\",\"employeeId\":2,\"password\":\"pass123\",\"username\":\"Jim\"}]");
    }

    @Test
    public void Test3GetEmployeeById() {
        //arrange & act
        final String json = target("/employees/2").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"{\"accountType\":\"BAR_MANAGER\",\"employeeId\":2,\"password\":\"pass123\",\"username\":\"Jim\"}");
    }

    @Test
    public void Test4GetEmployeeByAccountType() {
        //arrange & act
        final String json = target("/employees/account/BAR_MANAGER").request()
                .get(String.class);
        //assert
        Assertions.assertEquals(json,"[{\"accountType\":\"BAR_MANAGER\",\"employeeId\":2,\"password\":\"pass123\",\"username\":\"Jim\"}]");
    }

    @Test
    public void Test5UpdateEmployees() {
        //arrange
        Employee employee = target("/employees/2").request().get(Employee.class);
        employee.setUsername("Tom");

        //act
        Response response = target("/employees/2").request()
                .put(Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE));

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }

    @Test
    public void Test6deleteEmployees() {
        //arrange & act
        Response response = target("/employees/2").request().delete();

        //assert
        Assertions.assertEquals(204, response.getStatus());
    }
}
