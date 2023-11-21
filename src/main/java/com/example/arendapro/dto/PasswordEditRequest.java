package com.example.arendapro.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordEditRequest {
    String password;
    String rePassword;

    @Size(min = 8)
    String newPassword;
}
