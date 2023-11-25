package com.example.arendapro.dto;

import com.example.arendapro.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusDto {

    @JsonProperty(value = "status")
    private Status status;

}
