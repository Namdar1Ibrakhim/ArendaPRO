package com.example.arendapro.mapper;

import com.example.arendapro.dto.AddressRequestDto;
import com.example.arendapro.dto.AddressResponseDto;
import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.ImageData;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.Messages;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.repository.StorageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
//import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmovablesMapper{

    @Mapping(target = "images", expression = "java(mapToImageData(immovable.getImages()))")
    @Mapping(target = "addressResponseDto", expression = "java(getAddressResponseDto(immovable, addressMapper))")
    ImmovableResponseDto toDto(Immovables immovable, AddressMapper addressMapper);

//  @Mapping(target = "images", expression = "java(mapToImageDataList(immovableRequestDto.getImages(), repository))")
    Immovables toEntity(ImmovableRequestDto immovableRequestDto);

    List<ImmovableResponseDto> toDtoList(List<Immovables> list);

    default List<ImageData> mapToImageDataList(List<Integer> images, StorageRepository repository){
        List<ImageData> imageDataList = new ArrayList<>();
        for (Integer id : images){
            imageDataList.add(repository.findById(id).get());
        }
        return imageDataList;
    }
    default List<Integer> mapToImageData(List<ImageData> imageDataList){
        if (imageDataList==null){
            return null;
        }
        List<Integer> imageData = new ArrayList<>();
        for (ImageData image : imageDataList){
            imageData.add(image.getId());
        }
        return imageData;
    }
    default AddressResponseDto getAddressResponseDto(Immovables immovables, AddressMapper addressMapper){
        Address address = immovables.getAddress();
        return addressMapper.toDto(address);
    }

}
