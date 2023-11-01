package com.example.arendapro.dto;

import com.example.arendapro.entity.address.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddressRequestDto {

    private Integer country_id;
    private Integer region_id;
    private Integer city_id;
    private Integer district_id;
    private Integer street_id;
    private Integer immovableNumber;

}
