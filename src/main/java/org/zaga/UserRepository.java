package org.zaga;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {
    
    public UserModel getUser(String companyEmail){
        return find("companyEmail",companyEmail).firstResult();
    }
}
