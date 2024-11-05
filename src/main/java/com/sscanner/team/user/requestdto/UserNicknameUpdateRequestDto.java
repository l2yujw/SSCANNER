package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "사용자 닉네임 수정 요청 DTO")
public record UserNicknameUpdateRequestDto(

        @Schema(description = "새 닉네임", example = "newNickname123")
        @NotBlank(message = "새 닉네임을 입력해주세요.")
        String newNickname
) { }
