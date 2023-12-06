package com.example.arendapro.service.moderator;

import com.example.arendapro.dto.ImmovableResponseDto;
import com.example.arendapro.enums.Status;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAuthority('MODERATOR')")
public interface ModeratorService {

    ImmovableResponseDto getWaitingImmovable();

    void changeImmovableStatus(Integer immovable_id, Status status);
}
