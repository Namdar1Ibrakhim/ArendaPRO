package com.example.arendapro.dto;

import lombok.Data;

@Data
public class MessageResponseDto {
    private Integer sender_id;
    private Integer receiver_id;
    private String text;
}
