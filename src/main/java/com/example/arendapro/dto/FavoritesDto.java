package com.example.arendapro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritesDto {

    private Integer user_id;
    private Integer immovableId;
}
