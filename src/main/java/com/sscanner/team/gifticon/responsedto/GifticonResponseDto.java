package com.sscanner.team.gifticon.responsedto;

import com.sscanner.team.products.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기프티콘 응답 DTO")
public record GifticonResponseDto(
        @Schema(description = "제품 이름", example = "아메리카노")
        String productName,

        @Schema(description = "제품 이미지 URL", example = "https://example.com/product.jpg")
        String productImageUrl,

        @Schema(description = "바코드 이미지 URL", example = "https://example.com/barcode.jpg")
        String barcodeImageUrl
) {
    public static GifticonResponseDto of(Product product, String productImageUrl, String barcodeImageUrl) {
        return new GifticonResponseDto(
                product.getName(),
                productImageUrl,
                barcodeImageUrl
        );
    }
}
