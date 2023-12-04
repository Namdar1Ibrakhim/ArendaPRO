package com.example.arendapro.service.address.impl;

import com.example.arendapro.entity.address.*;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.repository.*;
import com.example.arendapro.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public void addAddress(Address address) {
        addressRepository.save(address);
        log.info("Адрес добавлен "+ address.toString());
    }

    @Override
    @Transactional
    public void deleteAddress(Integer address_id) {
        addressRepository.deleteById(address_id);
    }


}
