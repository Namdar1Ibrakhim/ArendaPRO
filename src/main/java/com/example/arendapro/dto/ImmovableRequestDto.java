package com.example.arendapro.dto;

import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ImmovableRequestDto {

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "NumOfRooms is required")
    private Integer numOfRooms;

    @NotNull(message = "Area is required")
    private Double area;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Long price;

    @NotNull(message = "PropertyType is required")
    private PropertyType propertyType;

    @NotNull(message = "State is required")
    private State state;

    @NotNull(message = "AddressRequestDto is required")
    private AddressRequestDto addressRequestDto;
}
