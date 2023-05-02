package com.exam.examserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname ;
    private String email;
    private String phoneNumber;
    private boolean enable = true;
    private String profile;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> usersRole = new HashSet<>();

    @Override
    public int hashCode() {
        return 31;
    }

}
