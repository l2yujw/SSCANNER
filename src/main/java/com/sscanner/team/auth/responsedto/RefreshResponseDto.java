package com.sscanner.team.auth.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "새로운 액세스 및 리프레시 토큰을 포함하는 응답 DTO")
public class RefreshResponseDto {

    @Schema(description = "새로 발급된 액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "새로 발급된 리프레시 토큰", example = "dGhpc0lzUmVmcmVzaFRva2Vu...")
    private String refreshToken;

    public static RefreshResponseDto of(String accessToken, String refreshToken) {
        return new RefreshResponseDto(accessToken, refreshToken);
    }
}
