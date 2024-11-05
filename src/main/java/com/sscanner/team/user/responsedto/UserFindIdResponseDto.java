package com.sscanner.team.user.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 ID 찾기 응답 DTO")
public record UserFindIdResponseDto(

        @Schema(description = "사용자의 이메일", example = "user@example.com")
        String email
) {
}
