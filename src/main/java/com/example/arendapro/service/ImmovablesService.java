package com.example.arendapro.service;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.exceptions.AccessDeniedException;
import com.example.arendapro.security.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImmovablesService {
    ImmovableResponseDto addImmovable(ImmovableRequestDto immovablesDto, User user) throws IOException;
    void deleteImmovable(Integer immovables_id, User user) throws Exception;
    ImmovableResponseDto editImmovable(Integer immovable_id, ImmovableRequestDto immovableDto, User user) throws AccessDeniedException, IOException;
    List<ImmovableResponseDto> getAllImmovables();
    ImmovableResponseDto findImmovable(Integer immovables_id);
    List<ImmovableResponseDto> findMyImmovables(User user);
    List<ImmovableResponseDto> findImmovablesByOwner(Integer owner_id);
}
