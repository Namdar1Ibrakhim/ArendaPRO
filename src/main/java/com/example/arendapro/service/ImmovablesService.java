package com.example.arendapro.service;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.PropertyType;
import com.example.arendapro.enums.State;
import com.example.arendapro.enums.Status;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImmovablesService {
    ImmovableResponseDto addImmovable(ImmovableRequestDto immovablesDto, User user) throws IOException;
    void deleteImmovable(Integer immovables_id, User user) throws Exception;
    ImmovableResponseDto editImmovable(Integer immovable_id, ImmovableRequestDto immovableDto, User user) throws AccessDeniedException, IOException;
    List<ImmovableResponseDto> getAllActiveImmovables(int page, int limit);
    ImmovableResponseDto getImmovableById(Integer immovables_id);
    List<ImmovableResponseDto> getAllMyImmovables(User user);
    List<ImmovableResponseDto> getActiveImmovablesByOwner(Integer owner_id);

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    List<ImmovableResponseDto> getAllImmovables();

    void changeStatus(Integer immovable_id, Status status, User user) throws AccessDeniedException;

    List<ImmovableResponseDto> filterImmovables(Long minPrice, Long maxPrice, Integer minNumOfRooms, Integer maxNumOfRooms, Double minArea, Double maxArea, State state, PropertyType propertyType);

    void uploadImage(Integer id, MultipartFile image, User user) throws AccessDeniedException;

    List<ImmovablesCache> getFromCache();
    void deleteCache(Integer immovable_id);

    void deleteAllCache();
}
