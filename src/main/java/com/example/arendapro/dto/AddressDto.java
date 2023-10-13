package com.example.arendapro.dto;

import com.example.arendapro.entity.address.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {

    private Country country_id;
    private Region region_id;
    private City city_id;
    private District district_id;
    private Street street_id;
    private int immovableNumber;

}
