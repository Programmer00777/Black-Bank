package com.blackbank.app.models;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

}
