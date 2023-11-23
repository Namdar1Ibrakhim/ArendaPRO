package com.example.arendapro.dto;

import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ImmovableRequestDto {

    private String title;
    private Integer numOfRooms;
    private Double area;
    private String description;
    private Long price;
    private PropertyType propertyType;
    private State state;
    private AddressRequestDto addressRequestDto;
}
