package com.example.arendapro.security.config;

import com.example.arendapro.security.user.Role;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var adminUser = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("Adminuser2004"))
                .role(Role.ADMIN)
                .build();
            userRepository.save(adminUser);
    }

}
