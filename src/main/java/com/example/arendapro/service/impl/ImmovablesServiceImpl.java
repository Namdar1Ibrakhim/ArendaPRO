package com.example.arendapro.service.impl;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.repository.AddressRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.AddressService;
import com.example.arendapro.service.ImmovablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImmovablesServiceImpl implements ImmovablesService {

    private final ImmovablesRepository immovablesRepository;
    private final ImmovablesMapper immovablesMapper;
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Override
    public ImmovableResponseDto addImmovable(ImmovableRequestDto immovablesDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).get();

        Address address = addressMapper.toEntity(immovablesDto.getAddressRequestDto());
        addressService.addAddress(address);

        Immovables immovables = immovablesMapper.toEntity(immovablesDto);
        immovables.setOwner(user);
        immovables.setAddress(address);
        immovablesRepository.save(immovables);
        return immovablesMapper.toDto(immovables);
    }

    @Override
    public void deleteImmovable(Integer immovables_id) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userRepository.findByEmail(auth.getName()).get();
        Immovables immovables = immovablesRepository.findById(immovables_id).get();
        if(!immovables.getOwner().equals(owner)){
            throw new Exception();
        }
        immovablesRepository.delete(immovables);
    }

    @Override
    public ImmovableResponseDto editImmovable(ImmovableRequestDto immovablesDto) {
        return null;
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
    public List<ImmovableResponseDto> findMyImmovables() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userRepository.findByEmail(auth.getName()).get();
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovable : immovablesRepository.findImmovablesByOwner(owner)){
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
