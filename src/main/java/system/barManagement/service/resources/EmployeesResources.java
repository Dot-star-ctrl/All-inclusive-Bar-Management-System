package system.barManagement.service.resources;

import system.barManagement.service.JWTTokenNeeded;
import system.barManagement.service.controllers.EmployeesController;
import system.barManagement.service.model.AccountType;
import system.barManagement.service.model.Employee;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@JWTTokenNeeded
@Path("/employees")
public class EmployeesResources {

    @Context
    private UriInfo uriInfo;
    SecurityContext securityContext;
    private static final EmployeesController employeesController = new EmployeesController();

    @GET //GET at http://localhost:XXXX/employees/
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        List<Employee> employees;
        employees = employeesController.showAllEmployees();
        return Response.ok(employees).build();
    }

    @GET //GET at http://localhost:XXXX/employees/2
    @RolesAllowed("BAR_MANAGER")
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") int id) {
        Employee employee = employeesController.showEmployee(id);
        if (employee == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please provide a valid employee id.").build();
        } else {
            return Response.ok(employee).build();
        }
    }

    @GET //GET at http://localhost:XXXX/employees/account/BAR_MANAGER
    @RolesAllowed("BAR_MANAGER")
    @Path("/account/{accountType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByAccountType(@PathParam("accountType") AccountType accountType) {
        List<Employee> employees = employeesController.getEmployeesByAccountType(accountType);
        if (employees == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("There are no " + accountType+ "s").build();
        } else {
            return Response.ok(employees).build();
        }
    }


    @POST //POST at http://localhost:XXXX/employees/
    @RolesAllowed("BAR_MANAGER")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) {
        if (!employeesController.addEmployee(employee)){
            String entity =  "Employee with id " + employee.getEmployeeId() + " already exists.";
            return Response.status(Response.Status.CONFLICT).entity(entity).build();
        } else {
            String url = uriInfo.getAbsolutePath() + "/" + employee.getEmployeeId(); // url of the created student
            URI uri = URI.create(url);
            return Response.created(uri).build();
        }
    }

    @PUT //PUT at http://localhost:XXXX/employees/
    @RolesAllowed("BAR_MANAGER")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateEmployee(Employee employee) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (employeesController.updateEmployee(employee)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Please provide a valid employee id.").build();
        }
    }

    @DELETE //DELETE at http://localhost:XXXX/employees/3
    @RolesAllowed("BAR_MANAGER")
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        employeesController.deleteEmployee(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return Response.noContent().build();
    }
}
