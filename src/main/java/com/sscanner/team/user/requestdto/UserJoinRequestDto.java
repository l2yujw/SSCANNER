package com.sscanner.team.user.requestdto;

import com.sscanner.team.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "사용자 회원가입 요청 DTO")
public record UserJoinRequestDto(

        @Schema(description = "사용자 이메일", example = "user@example.com")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일이 비어있습니다.")
        String email,

        @Schema(description = "비밀번호", example = "password123!")
        @NotBlank(message = "비밀번호가 비어있습니다.")
        String password,

        @Schema(description = "비밀번호 확인", example = "password123!")
        @NotBlank(message = "비밀번호 확인이 비어있습니다.")
        String passwordCheck,

        @Schema(description = "사용자 닉네임", example = "nickname123")
        @NotBlank(message = "닉네임이 비어있습니다.")
        String nickname,

        @Schema(description = "11자리 휴대폰 번호", example = "01012345678")
        @Pattern(
                regexp = "^\\d{11}$",
                message = "핸드폰 번호는 11자리 숫자만 입력해야 합니다."
        )
        @NotBlank(message = "전화번호가 비어있습니다.")
        String phone,

        @Schema(description = "6자리 인증 번호", example = "123456")
        @Pattern(
                regexp = "^\\d{6}$",
                message = "인증 번호는 6자리 숫자만 입력해야 합니다."
        )
        @NotBlank(message = "인증번호가 비어있습니다.")
        String smsCode

) {

    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .phone(phone)
                .authority("ROLE_USER")
                .build();
    }
}
