package com.example.arendapro.dto;

import com.example.arendapro.entity.address.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Valid
public class AddressRequestDto {

    @NotNull(message = "Country_id is required")
    private Integer country_id;

    @NotNull(message = "Region_id is required")
    private Integer region_id;

    @NotNull(message = "City_id is required")
    private Integer city_id;

    @NotNull(message = "District_id is required")
    private Integer district_id;

    @NotNull(message = "Street_id is required")
    private Integer street_id;

    @NotNull(message = "ImmovableNumber is required")
    private Integer immovableNumber;

}
