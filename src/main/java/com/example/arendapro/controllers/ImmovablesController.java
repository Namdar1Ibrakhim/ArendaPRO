package com.example.arendapro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImmovablesController {

    private final AddressController addressController;
    private final ImmovablesController immovablesController;


}
