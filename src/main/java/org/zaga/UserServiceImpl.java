package org.zaga;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repo;

    @Override
    public UserModel createUser(UserModel user) {
        // Save the user details 
        UserModel.persist(user);
        return user;
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
    
}
