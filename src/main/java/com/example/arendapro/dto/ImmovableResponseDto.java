package com.example.arendapro.dto;

import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImmovableResponseDto {
    private String title;
    private int numOfRooms;
    private double area;
    private String description;
    private long price;
    private PropertyType propertyType;
    private State state;
    private UserDto owner;
    private AddressResponseDto addressResponseDto;
}
