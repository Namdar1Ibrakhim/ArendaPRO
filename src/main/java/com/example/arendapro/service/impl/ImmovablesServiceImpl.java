package com.example.arendapro.service.impl;

import com.example.arendapro.dto.AddressDto;
import com.example.arendapro.dto.ImmovablesDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.security.user.User;
import com.example.arendapro.security.user.UserRepository;
import com.example.arendapro.service.ImmovablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImmovablesServiceImpl implements ImmovablesService {

    private final ImmovablesRepository immovablesRepository;
    private final ImmovablesMapper immovablesMapper;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    public ImmovablesDto addImmovables(ImmovablesDto immovablesDto, AddressDto addressDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).get();

        Immovables immovables = immovablesMapper.toEntity(immovablesDto);
        immovables.setOwner(user);
        immovables.setAddress(addressMapper.toEntity(addressDto));
        return immovablesMapper.toDto(immovables);
    }

    @Override
    public void deleteImmovables(Integer immovables_id) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userRepository.findByEmail(auth.getName()).get();
        Immovables immovables = immovablesRepository.findById(immovables_id).get();
        if(!immovables.getOwner().equals(owner)){
            throw new Exception();
        }
        immovablesRepository.delete(immovables);
    }
}
