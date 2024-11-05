package com.sscanner.team.products.responsedto;

import com.sscanner.team.products.entity.ProductImg;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 이미지 응답 DTO")
public record ProductImgResponseDto(
        @Schema(description = "상품 ID", example = "1") Long productId,
        @Schema(description = "이미지 URL", example = "https://example.com/image1.png") String url
) {
    public static ProductImgResponseDto from(ProductImg productImg) {
        return new ProductImgResponseDto(
                productImg.getProductId(),
                productImg.getUrl()
        );
    }
}
