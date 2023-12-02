package com.example.arendapro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDto{

    @NotNull(message = "First name is required")
    private String firstname;

    @NotNull(message = "Last name is required")
    private String lastname;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone Number is required")
//    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}")
    private String phoneNumber;
}
