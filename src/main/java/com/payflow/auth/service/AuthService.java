/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payflow.auth.service;

import com.payflow.auth.dto.RegisterRequest;
import com.payflow.auth.dto.RegisterResponse;
import com.payflow.common.exception.DuplicateResourceException;
import com.payflow.user.entity.Role;
import com.payflow.user.entity.User;
import com.payflow.user.repository.RoleRepository;
import com.payflow.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import static org.hibernate.internal.util.PropertiesHelper.map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */

@Service
public class AuthService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public RegisterResponse registerResponse (RegisterRequest registerRequest){
        String email = registerRequest.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException(
                    "email",
                    "Email này đã được đăng ký"
            );
        }
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("Role USER không tồn tại"));
        String encodePassword = passwordEncoder.encode(registerRequest.getPassword());
        String phone = registerRequest.getPhone();
        if (phone != null) {
            phone = phone.trim();
        }
        if (phone != null && userRepository.existsByPhone(phone)) {
            throw new DuplicateResourceException(
                    "phone",
                    "Số điện thoại đã tồn tại"
            );
        }
        User user = new User(
                email,
                encodePassword,
                registerRequest.getFullName().trim(),
                phone
        );
        user.addRole(role);
        User savedUser = userRepository.save(user);
        Set<String> roles = savedUser.getRoles()
            .stream()
            .map(Role::getName)
            .collect(Collectors.toSet());
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                roles,
                savedUser.getStatus(),
                savedUser.getCreatedAt()
        );
    }
}
