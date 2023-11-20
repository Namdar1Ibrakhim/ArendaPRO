package com.example.arendapro.service.impl;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.repository.StorageRepository;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.AddressService;
import com.example.arendapro.service.ImmovablesService;
import com.example.arendapro.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImmovablesServiceImpl implements ImmovablesService {

    private final ImmovablesRepository immovablesRepository;
    private final ImmovablesMapper immovablesMapper;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final StorageService storageService;

    @Override
    public ImmovableResponseDto addImmovable(ImmovableRequestDto immovableDto, User user) throws IOException {
        Address address = addressMapper.toEntity(immovableDto.getAddressRequestDto());
        addressService.addAddress(address);

        Immovables immovables = immovablesMapper.toEntity(immovableDto);
        immovables.setOwner(user);
        immovables.setAddress(address);
        immovables.setCreatedAt(new Date());
        immovablesRepository.save(immovables);

        return immovablesMapper.toDto(immovables);
    }

    @Override
    public void deleteImmovable(Integer immovables_id, User owner) throws Exception {
        Immovables immovables = immovablesRepository.findById(immovables_id).get();
        if(!immovables.getOwner().equals(owner)){
            throw new Exception();
        }
        immovablesRepository.delete(immovables);
    }

    @Override
    public ImmovableResponseDto editImmovable(Integer immovable_id, ImmovableRequestDto immovableDto, User user) throws AccessDeniedException, IOException {
        Immovables immovables = immovablesRepository.findById(immovable_id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not fount with id: " + immovable_id));
        if(immovables.getOwner().equals(user)){
            immovables = immovablesMapper.toEntity(immovableDto);
            if(immovableDto.getAddressRequestDto()!=null) {
                Address address = addressMapper.toEntity(immovableDto.getAddressRequestDto());
                addressService.addAddress(address);
                immovables.setAddress(address);
            }
            immovablesRepository.save(immovables);

            return immovablesMapper.toDto(immovables);
        }else{
            throw new AccessDeniedException("Access denied");
        }

    }

    @Override
    public List<ImmovableResponseDto> getAllImmovables() {
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovables : immovablesRepository.findAll()){
            list.add(immovablesMapper.toDto(immovables));
        }
        return list;
    }

    @Override
    public ImmovableResponseDto findImmovable(Integer immovables_id) {
        Immovables immovables = immovablesRepository.findById(immovables_id).get();
        return immovablesMapper.toDto(immovables);
    }

    @Override
    public List<ImmovableResponseDto> findMyImmovables(User user) {
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovable : immovablesRepository.findImmovablesByOwner(user)){
            list.add(immovablesMapper.toDto(immovable));
        }
        return list;
    }

    @Override
    public List<ImmovableResponseDto> findImmovablesByOwner(Integer owner_id) {
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovable : immovablesRepository.findImmovablesByOwner_Id(owner_id)){
            list.add(immovablesMapper.toDto(immovable));
        }
        return list;
    }

}
