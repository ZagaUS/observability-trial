package org.zaga;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserModel> {
    
    public UserModel getUser(String companyEmail){
        return find("companyEmail",companyEmail).firstResult();
    }

    public UserModel checkEmail(UserModel userModel){
        PanacheQuery<UserModel> user = UserModel.find("companyEmail=?1", userModel.getCompanyEmail());
        UserModel details = user.firstResult();
        return details;
    }
}
