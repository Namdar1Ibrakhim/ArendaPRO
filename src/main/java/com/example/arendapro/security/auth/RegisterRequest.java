package com.example.arendapro.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
