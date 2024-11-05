package com.sscanner.team.user.responsedto;

import com.sscanner.team.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원가입 응답 DTO")
public record UserJoinResponseDto(

        @Schema(description = "사용자의 ID", example = "user123")
        String userId,

        @Schema(description = "사용자의 이메일", example = "user@example.com")
        String email,

        @Schema(description = "사용자의 닉네임", example = "userNickname")
        String nickname,

        @Schema(description = "사용자의 전화번호", example = "01012345678")
        String phone
) {

    public static UserJoinResponseDto from(User user) {
        return new UserJoinResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname(),
                user.getPhone()
        );
    }
}
