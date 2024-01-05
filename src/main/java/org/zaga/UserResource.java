package org.zaga;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

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
        // try{
         // Generate random username and password based on the specified criteria
        String username = generateUsername(user.getName());
        String password = generateRandomPassword();

        // Set user details
        UserModel newUser = new UserModel();
        newUser.setName(user.getName());
        newUser.setPhoneNo(user.getPhoneNo());
        newUser.setLocation(user.getLocation());
        // newUser.setAddress(user.getAddress());
        newUser.setCompanyEmail(user.getCompanyEmail());
        newUser.setCompanyName(user.getCompanyName());

        // Set generated username and password
        newUser.setUsername(username);
        newUser.setPassword(password);
        // sendCredentialsByEmail(newUser.getCompanyEmail(), newUser.getUsername(), newUser.getPassword());
        // Persist user details in the database
        UserModel createdUser = userService.createUser(newUser);

            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        // } catch (Exception e) {
        //     return Response.serverError().entity("Failed to create user").build();
        // }
    }

    // private void sendCredentialsByEmail(String userEmail, String username, String password) {
    //     try {
    //         // Construct email body with username and password
    //         String emailBody = "Username: " + username + "\nPassword: " + password;

    //         // Use your email service or producer template to send the email
    //         // emailService.sendMail(userEmail, "Your Subject", emailBody);
    //         producerTemplate.sendBodyAndHeaders("direct:sendMailWithoutAttachment", emailBody, createEmailHeaders(userEmail));
    //     } catch (Exception e) {
    //         // Handle exceptions (log or throw as needed)
    //     }
    // }

    // private Map<String, Object> createEmailHeaders(String to) {
    //     Map<String, Object> headers = new HashMap<>();
    //     headers.put("To", to);
    //     headers.put("Subject", "Your Subject"); // Set your email subject here
    //     return headers;
    // }

    private String generateUsername(String name) {
        // Generate username with the first 4 letters of the name
        String firstFourLetters = name.length() >= 4 ? name.substring(0, 4) : name;
        String randomString = generateRandomString();
        return firstFourLetters + randomString;
    }

    private String generateRandomPassword() {
        // Generate a random password (customize as needed)
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
        // Generate a random string (customize as needed)
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
    
    
