package com.example.arendapro.service;

import com.example.arendapro.dto.ImmovableRequestDto;
import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.security.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImmovablesService {
    ImmovableResponseDto addImmovable(ImmovableRequestDto immovablesDto, User user, MultipartFile file) throws IOException;
    void deleteImmovable(Integer immovables_id) throws Exception;
    ImmovableResponseDto editImmovable(ImmovableRequestDto immovablesDto);
    List<ImmovableResponseDto> getAllImmovables();
    ImmovableResponseDto findImmovable(Integer immovables_id);
    List<ImmovableResponseDto> findMyImmovables();
    List<ImmovableResponseDto> findImmovablesByOwner(Integer owner_id);
}
