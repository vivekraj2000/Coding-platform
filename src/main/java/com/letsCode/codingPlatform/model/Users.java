package com.letsCode.codingPlatform.model;

import com.letsCode.codingPlatform.enums.UserRoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private String userName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
    private String firstName;
    private String lastName;

    public Users() {

    }
    public Users(String userName, String email, String password, UserRoleEnum role, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRoleEnum getRole() {
        return role;
    }
    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "Users [userName=" + userName + ", email=" + email + ", password=" + password + ", role=" + role
                + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
