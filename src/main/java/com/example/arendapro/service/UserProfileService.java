package com.example.arendapro.service;

import com.example.arendapro.security.user.User;

import java.util.Optional;

public interface UserProfileService {
    Optional<User> getCurrentUserDetails();
    Optional<User> getUserDetailsById(Integer id);
}
