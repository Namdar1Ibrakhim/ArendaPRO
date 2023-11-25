package com.example.arendapro.security.config;

import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        if(!userRepository.findByEmail("admin@gmail.com").isPresent()) {
            var adminUser = User.builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("Adminuser2004"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(adminUser);
        }
    }

}
