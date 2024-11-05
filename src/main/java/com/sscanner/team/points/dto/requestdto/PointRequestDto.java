package com.sscanner.team.points.dto.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "포인트 지급 요청 DTO")
public record PointRequestDto(
        @Schema(description = "지급할 포인트 수", example = "100")
        @NotNull(message = "지급할 points는 필수로 적어야 합니다.")
        Integer point
) {
}
