package com.example.arendapro.service;

import com.example.arendapro.dto.UserDto;

public interface UserProfileService {
    UserDto getCurrentUserDetails();
    UserDto getUserDetailsById(Integer id);
}
