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
    private int numOfRooms;
    private double area;
    private String description;
    private long price;
    private PropertyType propertyType;
    private State state;
    private AddressRequestDto addressRequestDto;
    private List<Integer> images;
}
