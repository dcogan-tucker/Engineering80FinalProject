package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginCredentialService implements UserDetailsService {

    private final UserRepository userRepository;

    public LoginCredentialService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getCurrentUser(String email){
        Optional<UserEntity> userOptional = userRepository.findUserEntityByEmailEquals(email);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        return userOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        UserEntity user = getCurrentUser(email);
        grantedAuthoritySet.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getEmail(), user.getPassword(), grantedAuthoritySet);
    }
}
