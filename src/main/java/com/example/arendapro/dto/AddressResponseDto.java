package com.example.arendapro.dto;

import com.example.arendapro.entity.address.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {
    private Country country;
    private Region region;
    private City city;
    private District district;
    private Street street;
    private Integer immovableNumber;

}
