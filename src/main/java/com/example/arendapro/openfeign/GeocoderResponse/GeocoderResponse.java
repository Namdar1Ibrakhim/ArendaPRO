package com.example.arendapro.openfeign.GeocoderResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeocoderResponse {

    @JsonProperty("GeoObjectCollection")
    private GeoObjectCollection geoObjectCollection;


}
