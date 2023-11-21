package com.example.arendapro.dto;

import com.example.arendapro.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private int errorCode;
}
