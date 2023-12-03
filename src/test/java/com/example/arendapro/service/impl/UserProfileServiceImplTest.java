package com.example.arendapro.service.impl;

import com.example.arendapro.dto.PasswordEditRequest;
import com.example.arendapro.dto.UserDto;
import com.example.arendapro.entity.User;
import com.example.arendapro.exceptions.PasswordMismatchException;
import com.example.arendapro.exceptions.UserNotFoundException;
import com.example.arendapro.mapper.UserMapper;
import com.example.arendapro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Test
    void testGetCurrentUserDetails() {
        User user = new User();
        UserDto expectedDto = new UserDto();
        when(mapper.toDto(user)).thenReturn(expectedDto);

        UserDto result = userProfileService.getCurrentUserDetails(user);

        assertEquals(expectedDto, result);
        verify(mapper, times(1)).toDto(user);
    }

    @Test
    void testGetUserDetailsById() throws UserNotFoundException {
        Integer userId = 1;
        User user = new User();
        UserDto expectedDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(mapper.toDto(user)).thenReturn(expectedDto);

        UserDto result = userProfileService.getUserDetailsById(userId);

        assertEquals(expectedDto, result);
        verify(userRepository, times(1)).findById(userId);
        verify(mapper, times(1)).toDto(user);
    }

    @Test
    void testGetUserDetailsByEmail() {
        String email = "test@example.com";
        User user = new User();
        UserDto expectedDto = new UserDto();
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));
        when(mapper.toDto(user)).thenReturn(expectedDto);

        UserDto result = userProfileService.getUserDetailsByEmail(email);

        assertEquals(expectedDto, result);
        verify(userRepository, times(1)).findByEmail(email);
        verify(mapper, times(1)).toDto(user);
    }

    @Test
    void testGetUserDetailsByEmail_UserNotFoundException() {
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userProfileService.getUserDetailsByEmail(email));
        verify(userRepository, times(1)).findByEmail(email);
        verify(mapper, never()).toDto(any());
    }

    @Test
    void testUpdateUserProfile() {
        UserDto userDto = new UserDto();
        User user = new User();
        when(mapper.toDto(user)).thenReturn(userDto);

        UserDto result = userProfileService.updateUserProfile(userDto, user);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).save(user);
        verify(mapper, times(1)).toDto(user);
    }

    @Test
    void testDeleteUserProfile() {
        User user = new User();

        userProfileService.deleteUserProfile(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testEditPassword() throws PasswordMismatchException {
        PasswordEditRequest request = new PasswordEditRequest();
        request.setNewPassword("newPassword");
        request.setRePassword("newPassword");

        User user = new User();
        when(passwordEncoder.encode(request.getNewPassword())).thenReturn("encodedPassword");

        userProfileService.editPassword(request, user);

        verify(passwordEncoder, times(1)).encode(request.getNewPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testEditPassword_PasswordMismatchException() {
        PasswordEditRequest request = new PasswordEditRequest();
        request.setNewPassword("newPassword");
        request.setRePassword("mismatchedPassword");

        User user = new User();

        assertThrows(PasswordMismatchException.class, () -> userProfileService.editPassword(request, user));
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }
}

