package com.example.arendapro.openfeign;

import com.example.arendapro.openfeign.GeocoderResponse.GeocoderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geocoder", url = "${name.service.geocoder.url}")
public interface YandexGeoсoderExample{

    @PostMapping("/geocoder")
    GeocoderResponse geocodeAddress( @RequestParam("apiKey") String apiKey,
                                     @RequestParam("geocode") String geocode
    );


}
