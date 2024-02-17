package com.wasd.usermicroservice.service.impl;

import com.wasd.usermicroservice.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
