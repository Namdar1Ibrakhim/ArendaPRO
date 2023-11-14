package com.example.arendapro.security.auth;


import com.example.arendapro.security.config.JwtService;
import com.example.arendapro.security.token.Token;
import com.example.arendapro.security.token.TokenRepository;
import com.example.arendapro.security.token.TokenType;
import com.example.arendapro.security.user.Role;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerUser(RegisterRequest request) {
        return register(request, Role.USER);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthenticationResponse registerModerator(RegisterRequest request) {
        return register(request, Role.MODERATOR);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        return register(request, Role.ADMIN);
    }

    private AuthenticationResponse register(RegisterRequest request, Role role) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        var savedUser = repository.save(user);
        var jwtToken = jwtService.genarateToken(user);

        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.genarateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
