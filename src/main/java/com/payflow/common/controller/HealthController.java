/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.payflow.common.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mac
 */
@RestController
public class HealthController {
    
    @GetMapping("/api/v1/health")
    public Map<String, String> health(){
        return Map.of(
                "status:", "UP",
                "application", "PayFlow"
        );
    }
    
}
