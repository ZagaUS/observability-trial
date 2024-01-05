package org.zaga;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class EmailModel extends PanacheEntity {
    private String from;
    private String to;
    private String subject;
    private String message;
    private String username;
    private String password;
    private String companyEmail;
    private String name;
}
