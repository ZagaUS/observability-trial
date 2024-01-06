package org.zaga;

import io.vertx.mutiny.ext.auth.User;

public interface UserService {
    UserModel createUser(UserModel user);
    UserModel getUserByEmail(String companyEmail);
    UserModel updatUser(String companyEmail, UserModel user);
    void deleteUser(String companyEmail);
    Boolean canCreate(UserModel userModel);
}
