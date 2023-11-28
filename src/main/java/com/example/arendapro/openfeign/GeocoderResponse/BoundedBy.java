package com.example.arendapro.openfeign.GeocoderResponse;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class BoundedBy {
    @JsonbProperty("Envelope")
    private Envelope envelope;

    // Геттеры и сеттеры
}
