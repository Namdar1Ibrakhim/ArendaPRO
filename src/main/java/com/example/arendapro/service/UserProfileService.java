package com.example.arendapro.service;

import com.example.arendapro.dto.PasswordEditRequest;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.exceptions.PasswordMismatchException;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.entity.User;

public interface UserProfileService {
    UserDto getCurrentUserDetails(User user);
    UserDto getUserDetailsById(Integer id) throws UserNotFoundException;
    UserDto updateUserProfile(UserDto userDto, User user);
    void deleteUserProfile(User user);
    void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException;
}
