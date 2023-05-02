package com.exam.examserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId ;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user ;

    @ManyToOne
    private Role role;

    @Override
    public int hashCode() {
        return 31 ;
    }
}
