package com.example.arendapro.service.moderator;

import com.example.arendapro.entity.Immovables;
import com.example.arendapro.enums.Status;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAuthority('MODERATOR')")
public interface ModeratorService {

    Immovables getWaitingImmovable();

    void changeImmovableStatus(Integer immovable_id, Status status);
}
