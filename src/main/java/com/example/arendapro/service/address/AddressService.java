package com.example.arendapro.service.address;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.entity.address.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressService {

    void addAddress(Address address);
    @PreAuthorize("hasAuthority('MODERATOR')")
    void deleteAddress(Integer address_id);

}
