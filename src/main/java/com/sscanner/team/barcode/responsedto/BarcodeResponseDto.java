package com.sscanner.team.barcode.responsedto;

import com.sscanner.team.barcode.entity.Barcode;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "바코드 응답 DTO")
public record BarcodeResponseDto(

        @Schema(description = "바코드 ID", example = "123")
        Long barcodeId,

        @Schema(description = "바코드 이미지 URL", example = "https://example.com/barcode-image.png")
        String barcodeUrl,

        @Schema(description = "상품 ID", example = "456")
        Long productId
) {
    public static BarcodeResponseDto from(Barcode barcode) {
        return new BarcodeResponseDto(
                barcode.getId(),
                barcode.getBarcodeUrl(),
                barcode.getProductId()
        );
    }
}
