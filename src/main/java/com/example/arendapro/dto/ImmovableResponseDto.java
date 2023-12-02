package com.example.arendapro.dto;

//import com.example.arendapro.entity.ImageData;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImmovableResponseDto {
    private String title;
    private Integer numOfRooms;
    private Double area;
    private String description;
    private Long price;
    private PropertyType propertyType;
    private State state;
    private UserDto owner;
    private AddressResponseDto addressResponseDto;

    private List<String> images;
}
