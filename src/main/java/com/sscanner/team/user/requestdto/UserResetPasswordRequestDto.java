package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "사용자 비밀번호 재설정 요청 DTO")
public record UserResetPasswordRequestDto(

        @Email(message = "유효한 이메일 형식이 아닙니다.")
        @NotNull(message = "아이디를 입력해주세요.")
        @Schema(description = "사용자의 이메일", example = "user@example.com")
        String email,

        @Pattern(
                regexp = "^\\d{11}$",
                message = "핸드폰 번호는 11자리 숫자만 입력해야 합니다."
        )
        @NotNull(message = "휴대폰 번호를 입력해주세요.")
        @Schema(description = "사용자의 휴대폰 번호", example = "01012345678")
        String phone,

        @Pattern(
                regexp = "^\\d{6}$",
                message = "인증번호는 6자리 숫자만 입력해야 합니다."
        )
        @NotNull(message = "인증번호를 입력해주세요.")
        @Schema(description = "인증번호", example = "123456")
        String code,

        @NotNull(message = "비밀번호를 입력해주세요.")
        @Schema(description = "새로운 비밀번호", example = "NewPassword123!")
        String newPassword
) {}
