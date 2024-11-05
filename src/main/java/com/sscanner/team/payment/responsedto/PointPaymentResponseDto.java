package com.sscanner.team.payment.responsedto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트 결제 응답 DTO")
public record PointPaymentResponseDto(
        @Schema(description = "사용자 ID", example = "user123")
        String userId,

        @Schema(description = "잔여 포인트", example = "500")
        Integer point
) {
    public static PointPaymentResponseDto of(String userId, Integer point) {
        return new PointPaymentResponseDto(userId, point);
    }
}
