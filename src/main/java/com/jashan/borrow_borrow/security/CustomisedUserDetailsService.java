package com.jashan.borrow_borrow.security;

import com.jashan.borrow_borrow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomisedUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(@NonNull String user_name) throws UsernameNotFoundException{
        return userRepository.findByUserName(user_name).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + user_name));
    }
}

