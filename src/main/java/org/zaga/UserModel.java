package org.zaga;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserDetails")
@JsonIgnoreProperties({"id",  "persistent"})
public class UserModel extends PanacheEntity {
    private String name;
    private Long phoneNo;
    private String companyEmail;
    private String companyName;
    private String location;
    private String role;
    private String department;
    private Long workPhone;
    // private String address;
    private String username;
    private String password;
    private LocalDate todayDate;
    private LocalDate expiryDate;

}

