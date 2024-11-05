package com.sscanner.team.products.responsedto;

import com.sscanner.team.products.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "상품 정보와 이미지 URL 리스트를 포함한 응답 DTO")
public record ProductWithImgResponseDto(
        @Schema(description = "상품 ID", example = "1") Long productId,
        @Schema(description = "상품 이름", example = "testProduct1") String productName,
        @Schema(description = "상품 가격", example = "100") Integer price,
        @Schema(description = "상품 이미지 URL 목록", example = "[\"https://example.com/image1.png\"]") List<String> imgUrls
) {
    public static ProductWithImgResponseDto from(Product product, List<String> imgUrls) {
        return new ProductWithImgResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                imgUrls
        );
    }
}
