package com.example.arendapro.security.auth;
import com.example.arendapro.security.token.Token;
import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.repository.UserRepository;
import com.example.arendapro.security.config.JwtService;
import com.example.arendapro.security.token.TokenRepository;
import com.example.arendapro.security.token.TokenType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testRegister() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@example.com", "123456", "password");
        Role role = Role.USER;

        // Stub repository methods
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Stub password encoder
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Stub the save method of repository
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1); // Assuming setId method is available in your User class
            return savedUser;
        });

        // Stub the genarateAccessToken and genarateRefreshToken methods
        when(jwtService.genarateAccessToken(any(User.class))).thenReturn("accessToken");
        when(jwtService.genarateRefreshToken(any(User.class))).thenReturn("refreshToken");

        // Act
        AuthenticationResponse response = authenticationService.register(registerRequest, role);

        // Assert
        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());
        // Add more assertions as needed
    }


    @Test
    void testAuthenticate() {
        // Arrange
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("john.doe@example.com", "password");
        User user = User.builder().id(1).firstname("John").lastname("Doe").email("john.doe@example.com").phoneNumber("123456").role(Role.USER).build();
        String jwtAccessToken = "mocked-access-token";
        String jwtRefreshToken = "mocked-refresh- token";
        Token token1 = new Token(1, jwtAccessToken, TokenType.BEARER, false, false, user);
        Token token2 = new Token(1, jwtRefreshToken, TokenType.BEARER, false, false, user);

        when(userRepository.findByEmail(authenticationRequest.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.genarateAccessToken(user)).thenReturn(jwtAccessToken);
        when(jwtService.genarateRefreshToken(user)).thenReturn(jwtRefreshToken);
        when(tokenRepository.findAllValidTokensByUser(user.getId())).thenReturn(Collections.singletonList(token1));

        // Act
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // Assert
        assertEquals(jwtAccessToken, response.getAccessToken());
        assertEquals(jwtRefreshToken, response.getRefreshToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, times(1)).findByEmail(authenticationRequest.getEmail());
        verify(jwtService, times(1)).genarateAccessToken(user);
        verify(jwtService, times(1)).genarateRefreshToken(user);
        verify(tokenRepository, times(1)).findAllValidTokensByUser(user.getId());
        verify(tokenRepository, times(2)).save(any(Token.class));
    }

    // Add more test methods for other scenarios...
}


