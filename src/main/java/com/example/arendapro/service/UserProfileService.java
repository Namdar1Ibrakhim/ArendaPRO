package com.example.arendapro.service;

import com.example.arendapro.dto.UserDto;
import com.example.arendapro.security.user.User;

public interface UserProfileService {
    UserDto getCurrentUserDetails(User user);
    UserDto getUserDetailsById(Integer id);
    UserDto updateUserProfile(UserDto userDto);
    void deleteUserProfile(User user);
}
