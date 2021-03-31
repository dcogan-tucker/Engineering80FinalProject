package com.sparta.eng80.onetoonetracker.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PasswordEncoder getBCryptPasswordEncoder() {
        return passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
