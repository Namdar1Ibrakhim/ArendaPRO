package com.example.arendapro.openfeign.GeocoderResponse;

import lombok.Data;

@Data
public class GeocoderResponseMetaData {
    private String request;
    private String found;
    private String results;
}
