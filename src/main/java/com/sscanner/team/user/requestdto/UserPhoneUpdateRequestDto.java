package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "사용자 핸드폰 번호 업데이트 요청 DTO")
public record UserPhoneUpdateRequestDto(

        @Pattern(
                regexp = "^\\d{11}$",
                message = "핸드폰 번호는 11자리 숫자만 입력해야 합니다."
        )
        @NotBlank(message = "새 핸드폰 번호를 입력해주세요.")
        @Schema(description = "새로운 핸드폰 번호", example = "01012345678")
        String newPhone,

        @Pattern(
                regexp = "^\\d{6}$",
                message = "인증번호는 6자리 숫자만 입력해야 합니다."
        )
        @NotBlank(message = "인증번호가 비어있습니다.")
        @Schema(description = "6자리 인증번호", example = "123456")
        String smsCode
) {}
