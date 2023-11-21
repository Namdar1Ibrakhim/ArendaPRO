package com.example.arendapro.model;

import com.example.arendapro.dto.ImmovableResponseDto;

import java.util.List;

public record ImmovableWithCountView(
        List<ImmovableResponseDto> immovableResponseDto,
        int count
) {
}
