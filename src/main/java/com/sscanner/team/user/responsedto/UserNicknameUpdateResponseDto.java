package com.sscanner.team.user.responsedto;

import com.sscanner.team.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 닉네임 수정 응답 DTO")
public record UserNicknameUpdateResponseDto(

        @Schema(description = "사용자의 ID", example = "user123")
        String userId,

        @Schema(description = "새로운 닉네임", example = "newNickname")
        String newNickname
) {
    public static UserNicknameUpdateResponseDto from(User user) {
        return new UserNicknameUpdateResponseDto(
                user.getUserId(),
                user.getNickname()
        );
    }
}
