package com.example.arendapro.service.impl;

import com.example.arendapro.dto.PasswordEditRequest;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.exceptions.PasswordMismatchException;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.entity.User;
import com.example.arendapro.repository.UserRepository;
import com.example.arendapro.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Cacheable(value = "UserProfileService::getUserDetailsById", key = "#id")
    public UserDto getUserDetailsById(Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return mapper.toDto(user);
    }

    @Override
    @SneakyThrows
//    @Cacheable(value = "UserProfileService::getUserDetailsByEmail", key = "#email")
    public UserDto getUserDetailsByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return mapper.toDto(user);
    }

    @Override
    @Transactional
//    @Caching(put = {
//            @CachePut(value = "UserProfileService::getUserDetailsById", key = "#user.id"),
//            @CachePut(value = "UserProfileService::getUserDetailsByEmail", key = "#user.email")
//    })
    public UserDto updateUserProfile(UserDto userDto, User user) {
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    @Transactional
//    @CacheEvict(value = "UserProfileService::getUserDetailsById", key = "#user.id")
    public void deleteUserProfile(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException {
        if(!request.getRePassword().equals(request.getNewPassword())) throw new PasswordMismatchException("Password mismatch");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
