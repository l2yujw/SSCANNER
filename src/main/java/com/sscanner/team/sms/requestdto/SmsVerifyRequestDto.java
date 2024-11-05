package com.sscanner.team.sms.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "SMS 인증 요청 DTO")
public record SmsVerifyRequestDto(

        @Schema(description = "휴대폰 번호 (11자리 숫자)", example = "01012345678")
        @Pattern(
                regexp = "^\\d{11}$",
                message = "핸드폰 번호는 11자리 숫자만 입력해야 합니다."
        )
        @NotNull(message = "휴대폰 번호를 입력해주세요.")
        String phoneNum,

        @Schema(description = "인증번호 (6자리 숫자)", example = "123456")
        @Pattern(
                regexp = "^\\d{6}$",
                message = "인증번호는 숫자만 입력해야 합니다."
        )
        @NotNull(message = "인증번호를 입력해주세요.")
        String code
) {
}
