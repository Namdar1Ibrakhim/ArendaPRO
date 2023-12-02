package com.example.arendapro.dto;

import com.example.arendapro.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusDto {

    @NotNull(message = "Status is required")
    @JsonProperty(value = "status")
    private Status status;

}
