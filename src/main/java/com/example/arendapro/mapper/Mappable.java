package com.example.arendapro.mapper;

import java.util.List;

public interface Mappable<E, D> {

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);

    E toEntity(D dto);

    List<E> toEntityLit(List<D> dtoList);
}
