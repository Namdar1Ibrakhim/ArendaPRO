package com.example.arendapro.service.impl;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.ImmovableImage;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.entity.address.Address;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.exceptions.EntityNotFoundException;
import com.example.arendapro.mapper.AddressMapper;
import com.example.arendapro.mapper.ImmovablesMapper;
import com.example.arendapro.rabbitmq.RabbitMQProducer;
import com.example.arendapro.repository.ImmovableImageRepository;
import com.example.arendapro.repository.ImmovablesRepository;
import com.example.arendapro.enums.Role;
import com.example.arendapro.entity.User;
import com.example.arendapro.service.AddressService;
import com.example.arendapro.service.ImageService;
import com.example.arendapro.service.ImmovablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final RabbitMQProducer producer;
    private final ImageService imageService;
    private final ImmovableImageRepository immovableImageRepository;


    @Override
    @Transactional
    public ImmovableResponseDto addImmovable(ImmovableRequestDto immovableDto, User user) throws IOException {
        log.info(immovableDto.getAddressRequestDto().toString());
        Address address = addressMapper.toEntity(immovableDto.getAddressRequestDto());
        addressService.addAddress(address);

        Immovables immovables = immovablesMapper.toEntity(immovableDto);

        immovables.setOwner(user);
        immovables.setAddress(address);
        immovables.setCreatedAt(new Date());
        immovables.setStatus(Status.MODERATION);
        immovablesRepository.save(immovables);

        producer.sendMessage(immovables.getId()+"");
        log.info("Message sent to RabbitMQ ...");

        return immovablesMapper.toDto(immovables, addressMapper);
    }

    @Override
    @Transactional
    public ImmovableResponseDto editImmovable(Integer immovable_id, ImmovableRequestDto immovableDto, User user) throws AccessDeniedException, IOException {
        Immovables immovables = immovablesRepository.findById(immovable_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovable_id));
        if(!immovables.getOwner().equals(user) || !user.getRole().equals(Role.MODERATOR)) throw new AccessDeniedException("Access Denied, you can't edit");

        immovables = immovablesMapper.toEntity(immovableDto);
        if(immovableDto.getAddressRequestDto()!=null) {
            Address address = addressMapper.toEntity(immovableDto.getAddressRequestDto());
            addressService.addAddress(address);
            immovables.setAddress(address);
        }
        immovables.setStatus(Status.MODERATION);
        immovablesRepository.save(immovables);

        producer.sendMessage(immovables.getId()+"");
        log.info("Message sent to RabbitMQ ...");

        return immovablesMapper.toDto(immovables, addressMapper);

    }

    @Override
    @Transactional
    public String deleteImmovable(Integer immovables_id, User owner) throws Exception {
        Immovables immovables = immovablesRepository.findById(immovables_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovables_id));

        log.info(owner.getRole().toString());
        if(!immovables.getOwner().equals(owner) && !owner.getRole().toString().equals("MODERATOR")) throw new AccessDeniedException("Access Denied, you can't delete");
        addressService.deleteAddress(immovables_id);
        immovablesRepository.delete(immovables);

        return "Successfully deleted";
    }

    @Override
    public List<ImmovableResponseDto> getAllActiveImmovables(int page, int limit) {
        if (page > 0) {
            page -= 1;
        }
        Pageable pageable = PageRequest.of(page, limit);
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovables : immovablesRepository.findByOrderByCreatedAtDesc(pageable)){
            list.add(immovablesMapper.toDto(immovables, addressMapper));
        }
        return list;
    }

    @Override
    public ImmovableResponseDto getActiveImmovableById(Integer immovables_id) {
        Immovables immovables = immovablesRepository.findByActiveId(immovables_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovables_id));
        return immovablesMapper.toDto(immovables, addressMapper);
    }

    @Override
    public List<ImmovableResponseDto> getAllMyImmovables(User user) {
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovable : immovablesRepository.findImmovablesByOwner(user)){
            list.add(immovablesMapper.toDto(immovable, addressMapper));
        }
        return list;
    }

    @Override
    public List<ImmovableResponseDto> getActiveImmovablesByOwner(Integer owner_id) {
        List<ImmovableResponseDto> list = new ArrayList<>();
        for(Immovables immovable : immovablesRepository.findImmovablesByOwner_Id(owner_id)){
            list.add(immovablesMapper.toDto(immovable, addressMapper));
        }
        return list;
    }
    @Override
    public List<ImmovableResponseDto> getAllImmovables() {
        return immovablesMapper.toDtoList(immovablesRepository.findAll());
    }

    @Override
    @Transactional
    public void changeStatus(Integer immovable_id, Status status, User user) throws AccessDeniedException {
        Immovables immovables = immovablesRepository.findByActiveId(immovable_id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + immovable_id));

        if(!immovables.getOwner().equals(user) && status.equals(Status.MODERATION)) throw new AccessDeniedException("Access Denied, you can't edit");

        if(status.equals("ACTIVE")){
            immovables.setStatus(Status.MODERATION);
        }else if(status.equals("ARCHIVE")){
            immovables.setStatus(Status.ARCHIVE);
        }
    }

    @Override
    public List<Immovables> filterImmovables(Long minPrice, Long maxPrice, Integer minNumOfRooms, Integer maxNumOfRooms, Double minArea, Double maxArea, State state, PropertyType propertyType) {
        return immovablesRepository.findByFilter(minPrice, maxPrice, minNumOfRooms, maxNumOfRooms, minArea, maxArea, state, propertyType);
    }

    @Override
    @Transactional
    public void uploadImage(Integer id, MultipartFile image) {
        Immovables immovables = immovablesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Immovable not fount with id: " + id));

        String fileName = imageService.upload(image);
        immovables.getImages().add(fileName);
        immovablesRepository.save(immovables);

        ImmovableImage immovableImage = ImmovableImage.builder()
                .immovable(immovables)
                .image(fileName).build();
        immovableImageRepository.save(immovableImage);
    }

}
