package org.zaga;

import org.jboss.logging.Logger;

import io.vertx.mutiny.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    Logger logger;

    @Inject
    UserRepository repo;

    @Override
    public UserModel createUser(UserModel user) {

        if (canCreate(user)){
        // Save the user details 
        UserModel.persist(user);
        return user;
        }

            else{
              logger.error("User Already Existed");
              throw new WebApplicationException("User Already Existed", 500); 
            }
    }

    @Override
    public UserModel getUserByEmail(String companyEmail) {
        return repo.getUser(companyEmail);
    }

    @Override
    public UserModel updatUser(String companyEmail, UserModel user) {
        UserModel details = repo.getUser(companyEmail);
        details.setPassword(user.getPassword());
        return details;
    }

    @Override
    public void deleteUser(String companyEmail) {
       UserModel user = repo.getUser(companyEmail);
       if (user != null ){
            repo.delete(user);
       }
    }

    @Override
    public Boolean canCreate(UserModel userModel) {
        UserModel userDetails = repo.checkEmail(userModel);
        if (userDetails==null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    
}
