package com.example.arendapro.dto;

import  jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordEditRequest {
    @NotNull(message = "Password is required")
    String password;

    @NotNull(message = "New password is required")

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}", message = "The line must be at least 8 characters, leave at least 1 capital letter, one abbreviated letter and special. OR. symbol OR numbers.")
    String newPassword;

    @NotNull(message = "Re-Password is required")
    String rePassword;
}
