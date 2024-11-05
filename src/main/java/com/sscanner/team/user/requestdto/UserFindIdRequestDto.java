package com.sscanner.team.user.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "사용자 ID 찾기 요청 DTO")
public record UserFindIdRequestDto(

        @Schema(description = "11자리 휴대폰 번호", example = "01012345678")
        @Pattern(
                regexp = "^\\d{11}$",
                message = "핸드폰 번호는 11자리 숫자만 입력해야 합니다."
        )
        @NotNull(message = "휴대폰 번호를 입력해주세요.")
        String phone,

        @Schema(description = "6자리 인증번호", example = "123456")
        @Pattern(
                regexp = "^\\d{6}$",
                message = "인증번호는 6자리 숫자만 입력해야 합니다."
        )
        @NotNull(message = "인증번호를 입력해주세요.")
        String code
) {
}
