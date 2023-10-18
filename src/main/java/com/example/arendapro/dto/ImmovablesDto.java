package com.example.arendapro.dto;

import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImmovablesDto {

    private String title;
    private int numOfRooms;
    private double area;
    private String description;
    private long price;
    private PropertyType propertyType;
    private State state;
}
