package com.example.arendapro.config;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.enums.Status;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImmovablesRepository immovablesRepository;

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
        startCheck();
    }
    public void startCheck() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run(){
                        Iterable<Immovables> immovables = immovablesRepository.findAll();
                        for (Immovables immovable : immovables) {
                            if (immovable.getCreatedAt().getTime()+(7*24*60*60*1000) < System.currentTimeMillis()) {
                                immovable.setStatus(Status.ARCHIVE);
                                immovablesRepository.save(immovable);
                                log.info("Immovable with id: "  +immovable.getId() + " moved to ARCHIVE");
                            }
                        }
                    }
                };
                timer.scheduleAtFixedRate(timerTask, 0, 24*60*60*1000);
            }
        });
        thread.start();
    }

}
