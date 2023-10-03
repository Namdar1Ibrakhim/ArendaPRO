package com.example.arendapro.service.impl;

import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName());
    }
    @Override
    public Optional<User> getUserDetailsById(Integer id) {
        return userRepository.findById(id);
    }
}
