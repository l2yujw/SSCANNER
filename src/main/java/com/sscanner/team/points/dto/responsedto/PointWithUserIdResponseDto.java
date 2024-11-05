package com.sscanner.team.points.dto.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 ID와 포인트 응답 DTO")
public record PointWithUserIdResponseDto(
        @Schema(description = "사용자 ID", example = "user123")
        String userId,

        @Schema(description = "사용자의 포인트", example = "500")
        Integer point
) {
    public static PointWithUserIdResponseDto of(String userId, Integer point) {
        return new PointWithUserIdResponseDto(userId, point);
    }
}
