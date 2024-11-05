package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "사용자 로그인 요청 DTO")
public record UserLoginRequestDto(

        @Schema(description = "사용자 이메일", example = "user@example.com")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일이 비어있습니다.")
        String email,

        @Schema(description = "사용자 비밀번호", example = "password123!")
        @NotBlank(message = "비밀번호가 비어있습니다.")
        String password
) {
}
