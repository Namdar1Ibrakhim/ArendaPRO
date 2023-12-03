package com.example.arendapro.openfeign;

import com.example.arendapro.openfeign.GeocoderResponse.GeocodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "geocodeClient", url = "https://geocode-maps.yandex.ru/1.x/")
public interface GeocodeClient {

    @GetMapping("")
    GeocodeResponse geocode(
            @RequestParam("apikey") String apiKey,
            @RequestParam("geocode") String location,
            @RequestParam("format") String format
    );
}
