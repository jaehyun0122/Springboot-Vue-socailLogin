package com.social_example_back.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.social_example_back.api.user.entity.MyUser;
import com.social_example_back.api.user.repository.UserRepository;
import com.social_example_back.oauth.entity.MyUserDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }
        return new MyUserDetails(myUser);
    }
}
