package com.sscanner.team.user.responsedto;

import com.sscanner.team.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "마이페이지 응답 DTO")
public record UserMypageResponseDto(

        @Schema(description = "사용자의 이메일", example = "user@example.com")
        String email,

        @Schema(description = "사용자의 닉네임", example = "userNickname")
        String nickname,

        @Schema(description = "사용자의 전화번호", example = "01012345678")
        String phone
) {
    public static UserMypageResponseDto create(User user) {
        return new UserMypageResponseDto(user.getEmail(), user.getNickname(), user.getPhone());
    }
}
