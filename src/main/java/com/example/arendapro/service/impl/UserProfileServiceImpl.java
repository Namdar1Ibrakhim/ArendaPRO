package com.example.arendapro.service.impl;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public UserDto getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).get();
        return mapper.toDto(user);

    }
    @Override
    public UserDto getUserDetailsById(Integer id) {
        User user = userRepository.findById(id).get();
        return mapper.toDto(user);
    }
}
