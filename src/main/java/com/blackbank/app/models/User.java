package com.blackbank.app.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 30, message = "Firstname must be between 3 and 30 characters")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3, max = 30, message = "Lastname must be between 3 and 30 characters")
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Pattern(regexp = "^(.+)@(.+)$")
    @Column(name = "email")
    private String email;

    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
