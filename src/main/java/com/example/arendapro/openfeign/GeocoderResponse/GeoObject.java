package com.example.arendapro.openfeign.GeocoderResponse;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class GeoObject {
    @JsonbProperty("metaDataProperty")
    private MetaDataProperty metaDataProperty;

    private String description;
    private String name;

    @JsonbProperty("boundedBy")
    private BoundedBy boundedBy;

    @JsonbProperty("Point")
    private Point point;

}