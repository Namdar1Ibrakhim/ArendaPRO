package com.example.arendapro.mapper;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.entity.Immovables;
import org.mapstruct.Mapper;

public interface ImmovablesCacheMapper {

    ImmovablesCache toCacheClass(Immovables immovables);

    Immovables toEntity(ImmovablesCache immovablesCache);

}
