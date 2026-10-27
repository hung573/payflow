/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payflow.auth.dto;

import com.payflow.user.entity.Role;
import com.payflow.user.enums.UserStatus;
import java.time.Instant;
import java.util.Set;

/**
 *
 * @author mac
 */
public class RegisterResponse {
    
    private long id;
    private String fullName;
    private String email;
    private Set<String> role;
    private UserStatus userStatus;
    private Instant createdAt;

    public RegisterResponse() {
    }

    public RegisterResponse(long id, String fullName, String email, Set<String> role, UserStatus userStatus, Instant createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
        this.createdAt = createdAt;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
    
    
    
    
}
