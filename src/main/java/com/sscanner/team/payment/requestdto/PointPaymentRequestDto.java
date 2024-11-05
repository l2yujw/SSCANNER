package com.sscanner.team.payment.requestdto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "포인트 결제 요청 DTO")
public record PointPaymentRequestDto(
        @Schema(description = "결제할 제품 ID", example = "12345")
        Long productId
) {
}
