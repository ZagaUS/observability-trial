package org.zaga;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.jboss.resteasy.reactive.RestResponse;

import io.vertx.mutiny.ext.auth.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
// import org.apache.camel.ProducerTemplate;


@Path("/trialuser")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @CrossOrigin
public class UserResource {

    @Inject
    UserService userService;
    

    // @Inject
    // ProducerTemplate producerTemplate;

    @POST
    @Path("/create")
    @Transactional
    public Response createUser(UserModel user){

        String username = generateUsername(user.getName());
        String password = generateRandomPassword();


        UserModel newUser = new UserModel();
        newUser.setName(user.getName());
        newUser.setPhoneNo(user.getPhoneNo());
        newUser.setLocation(user.getLocation());
        // newUser.setAddress(user.getAddress());
        newUser.setCompanyEmail(user.getCompanyEmail());
        newUser.setCompanyName(user.getCompanyName());


        newUser.setUsername(username);
        newUser.setPassword(password);

        UserModel createdUser = userService.createUser(newUser);
        System.out.println("----created user-- " + createdUser);

        return Response.status(Response.Status.CREATED).entity(createdUser).build();
        // return userService.createUser(newUser);
    }

    private String generateUsername(String name) {

        String firstFourLetters = name.length() >= 4 ? name.substring(0, 4) : name;
        String randomString = generateRandomString();
        return firstFourLetters + randomString;
    }

    private String generateRandomPassword() {

        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomPassword.append(characters.charAt(index));
        }
        return randomPassword.toString();
    }

    private String generateRandomString() {

        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }



}
    
    
