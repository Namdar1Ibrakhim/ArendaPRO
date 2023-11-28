package com.example.arendapro.openfeign.GeocoderResponse;

import com.example.arendapro.openfeign.GeocoderResponse.GeocoderResponseMetaData;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class MetaDataProperty {
    @JsonbProperty("GeocoderResponseMetaData")
    private GeocoderResponseMetaData geocoderResponseMetaData;

    // Геттеры и сеттеры
}
