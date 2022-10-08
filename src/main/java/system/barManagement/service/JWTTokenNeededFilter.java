package system.barManagement.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import system.barManagement.service.model.Employee;
import system.barManagement.service.repository.HibernateEmployeesRepository;
import system.barManagement.service.resources.AuthenticationEndpoint;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer=";

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println(authorizationHeader);
        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }
        
        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();
        try {

            System.out.println("second: " + AuthenticationEndpoint.key);
            Jwts.parserBuilder().setSigningKey(AuthenticationEndpoint.key).build().parseClaimsJws(token);
            System.out.println("#### valid token : " + token);

            String username = Jwts.parserBuilder().setSigningKey(AuthenticationEndpoint.key).build().parseClaimsJws(token).getBody().getSubject();


            /* Get information about the service method which is being called.
            This information includes the annotated/permitted roles. */
            Method method = resourceInfo.getResourceMethod();
            // if access is allowed for all -> do not check anything further : access is
            // approved for all
            if (method.isAnnotationPresent(PermitAll.class)) {
                return;
            }

            // if access is denied for all: deny access
            if (method.isAnnotationPresent(DenyAll.class)) {
                Response response = Response.status(Response.Status.FORBIDDEN).build();
                requestContext.abortWith(response);
                return;
            }

            /* here you do
            1. the AUTHENTICATION first (as explained in previous sections), and
            2. if AUTHENTICATION succeeds, you do the authorization like this:
            */

            if (method.isAnnotationPresent(RolesAllowed.class)) {
                // get allowed roles for this method
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

            /* isUserAllowed : implement this method to check if this user has any of
            the roles in the rolesSet
            if not isUserAllowed abort the requestContext with FORBIDDEN response*/

                if (!isUserAllowed(username, rolesSet)) {
                    abortWithUnauthorized(requestContext);
                    return;
                }
            }
        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
            System.out.println("#### invalid token : " + token);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private boolean isUserAllowed(String username, Set<String> rolesSet) {
        HibernateEmployeesRepository employeesRepository = new HibernateEmployeesRepository();

        try {
            for (Employee employee : employeesRepository.getEmployees()) {
                if (employee.getUsername().equals(username)) {
                    for (String role : rolesSet) {
                        if (employee.getAccountType().toString().equals(role)) {
                            System.out.println(employee.getAccountType().toString());
                            System.out.println(role);
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
        } catch (BarManagementDBException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
