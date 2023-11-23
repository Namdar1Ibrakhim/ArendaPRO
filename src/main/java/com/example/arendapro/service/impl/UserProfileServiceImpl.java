package com.example.arendapro.service.impl;

import com.example.arendapro.dto.PasswordEditRequest;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.exceptions.PasswordMismatchException;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public UserDto getCurrentUserDetails(User user) {
        return mapper.toDto(user);

    }
    @Override
    public UserDto getUserDetailsById(Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return mapper.toDto(user);
    }

    @Override
    public UserDto updateUserProfile(UserDto userDto, User user) {
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public void deleteUserProfile(User user) {
        userRepository.delete(user);
    }

    @Override
    public void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException {
        if(!request.getPassword().equals(request.getNewPassword())) throw new PasswordMismatchException("Password mismatch");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
