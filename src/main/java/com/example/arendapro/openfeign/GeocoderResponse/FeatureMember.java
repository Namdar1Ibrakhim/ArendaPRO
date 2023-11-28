package com.example.arendapro.openfeign.GeocoderResponse;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class FeatureMember {
    @JsonbProperty("GeoObject")
    private GeoObject geoObject;

}
