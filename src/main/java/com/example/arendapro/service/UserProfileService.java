package com.example.arendapro.service;

import com.example.arendapro.dto.PasswordEditRequest;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.exceptions.PasswordMismatchException;
import com.example.arendapro.security.user.User;

public interface UserProfileService {
    UserDto getCurrentUserDetails(User user);
    UserDto getUserDetailsById(Integer id);
    UserDto updateUserProfile(UserDto userDto, User user);
    void deleteUserProfile(User user);
    void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException;
}
