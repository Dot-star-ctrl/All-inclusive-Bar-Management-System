package system.barManagement.service.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import system.barManagement.service.BarManagementDBException;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.HibernateEmployeesRepository;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Path("/authentication")
public class AuthenticationEndpoint {

    public static SecretKey key;

    @Context
    UriInfo uriInfo;

    @POST//GET at http://localhost:XXXX/authentication/
//    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @FormParam("username") String username,
            @FormParam("password") String password){
    try{

            // Authenticate the user using the credentials provided
            if (!isValidUser(username, password)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
}

    private boolean isValidUser(String username, String password) {
        HibernateEmployeesRepository employeesRepository = new HibernateEmployeesRepository();

        try {
            for (Employee employee : employeesRepository.getEmployees()) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                    System.out.println(employee);
                    return true;
                }
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
           key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
           System.out.println("first: " + key);
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(86400l)))
                .signWith(key)
                .compact();
        return jwtToken;
    }
}

