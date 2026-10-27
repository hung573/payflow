/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payflow.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author mac
 */
public class RegisterRequest {
    
    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Email không hợp lệ!")
    @Size(max = 255, message = "Email tối đa 255 ký tự")
    private String email;
    
    @NotBlank(message = "Password không được để trống!")
    @Size(min = 8, max = 64, message = "Mật khẩu phải có từ 8 đến 64 ký tự")
    private String password;
    
    @NotBlank(message = "Họ và tên không được để trống!")
    @Size(max = 100, message = "Họ và tên không được quá 100 ký tự")
    private String fullName;
    
    @Pattern(
        regexp = "^\\+?[0-9]{9,15}$",
        message = "Số điện thoại phải có từ 9 đến 15 chữ số, có thể bắt đầu bằng +" 
    )
    private String phone;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String fullName, String phone) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
