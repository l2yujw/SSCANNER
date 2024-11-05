package com.sscanner.team.user.responsedto;

import com.sscanner.team.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 전화번호 수정 응답 DTO")
public record UserPhoneUpdateResponseDto(

        @Schema(description = "사용자의 ID", example = "user123")
        String userId,

        @Schema(description = "새로운 전화번호", example = "01012345678")
        String newPhone
) {
    public static UserPhoneUpdateResponseDto from(User user) {
        return new UserPhoneUpdateResponseDto(
                user.getUserId(),
                user.getPhone()
        );
    }
}
