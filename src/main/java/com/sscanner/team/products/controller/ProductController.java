package com.sscanner.team.products.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.products.responsedto.ProductImgResponseDto;
import com.sscanner.team.products.responsedto.ProductWithImgResponseDto;
import com.sscanner.team.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@ErrorApiResponses
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 목록 조회", description = "페이지와 사이즈를 기준으로 모든 상품을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공", content = @Content(mediaType = "application/json"))
    @GetMapping
    public BaseApiResponse<Map<String, Object>> findAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Map<String, Object> response = productService.findAllWithImgs(pageable);
        return BaseApiResponse.ok(200, response, "상품 목록 조회 성공");
    }

    @Operation(summary = "상품 단일 조회", description = "상품 ID를 기준으로 상품의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "상품 정보 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductWithImgResponseDto.class)))
    @GetMapping("/{productId}")
    public BaseApiResponse<ProductWithImgResponseDto> findProductById(@PathVariable Long productId) {
        ProductWithImgResponseDto product = productService.findWithImgById(productId);
        return BaseApiResponse.ok(200, product, "상품 정보 조회 성공");
    }

    @Operation(summary = "상품 이미지 등록", description = "상품 ID에 해당하는 이미지들을 업로드합니다.")
    @ApiResponse(responseCode = "200", description = "이미지 등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductImgResponseDto.class)))
    @PostMapping("/{productId}/images")
    public BaseApiResponse<List<ProductImgResponseDto>> uploadProductImages(
            @PathVariable Long productId,
            @RequestParam("images") List<MultipartFile> files
    ) {
        List<ProductImgResponseDto> response = productService.addImages(productId, files);
        return BaseApiResponse.ok(200, response, "이미지 등록 성공");
    }
}
