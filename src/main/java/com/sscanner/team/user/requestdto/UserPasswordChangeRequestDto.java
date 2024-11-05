package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "사용자 비밀번호 변경 요청 DTO")
public record UserPasswordChangeRequestDto(

        @Schema(description = "현재 비밀번호", example = "currentPassword123")
        @NotBlank(message = "현재 비밀번호를 입력해주세요.")
        String currentPassword,

        @Schema(description = "새 비밀번호", example = "newPassword123")
        @NotBlank(message = "새 비밀번호를 입력해주세요.")
        String newPassword,

        @Schema(description = "새 비밀번호 확인", example = "newPassword123")
        @NotBlank(message = "새 비밀번호 확인을 입력해주세요.")
        String confirmNewPassword
) {
}
