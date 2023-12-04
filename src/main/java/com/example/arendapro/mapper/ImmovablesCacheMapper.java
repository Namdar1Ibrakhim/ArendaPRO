package com.example.arendapro.mapper;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.entity.Immovables;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmovablesCacheMapper {

    ImmovablesCache toCacheClass(Immovables immovables);

    Immovables toEntity(ImmovablesCache immovablesCache);

//    List<ImmovableResponseDto> toDtoList(List<Immovables> list);



}
