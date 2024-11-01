package com.sscanner.team.points.dto.requestdto;


import jakarta.validation.constraints.NotNull;

public record PointRequestDto(
        @NotNull(message = "지급할 points는 필수로 적어야 합니다.")
        Integer point
) {
}
