package com.example.arendapro.mapper.impl;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesCacheMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImmovablesCacheMapperImpl implements ImmovablesCacheMapper {
    @Override
    public ImmovablesCache toCacheClass(Immovables immovables) {
        ImmovablesCache immovablesCache = new ImmovablesCache();
        immovablesCache.setId(immovables.getId());
        immovablesCache.setTitle(immovables.getTitle());
        immovablesCache.setNumOfRooms(immovables.getNumOfRooms());
        immovablesCache.setArea(immovables.getArea());
        immovablesCache.setDescription(immovables.getDescription());
        immovablesCache.setPrice(immovables.getPrice());
        immovablesCache.setCreatedAt(immovables.getCreatedAt());
        immovablesCache.setOwner(immovables.getOwner().getFirstname()+ " " + immovables.getOwner().getLastname());
        immovablesCache.setPropertyType(immovables.getPropertyType().toString());
        immovablesCache.setState(immovables.getState().toString());
        immovablesCache.setStatus(immovables.getStatus().toString());
        immovablesCache.setAddress(immovables.getAddress().getRegion().getName() + "," + immovables.getAddress().getCity().getName() + ", " + immovables.getAddress().getDistrict().getName() + ", " + immovables.getAddress().getStreet().getName() + ", immovable #" + immovables.getAddress().getImmovableNumber());


        return immovablesCache;
    }

    @Override
    public Immovables toEntity(ImmovablesCache immovablesCache) {
        Immovables immovables = new Immovables();

        return immovables;
    }
}
