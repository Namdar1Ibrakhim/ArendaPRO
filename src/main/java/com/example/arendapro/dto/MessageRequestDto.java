package com.example.arendapro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequestDto {

    @NotNull(message = "Receiver_id is required")
    private Integer receiver_id;

    @NotNull(message = "Text is required")
    private String text;
}
