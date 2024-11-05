package com.sscanner.team.barcode.controller;

import com.sscanner.team.barcode.responsedto.BarcodeResponseDto;
import com.sscanner.team.barcode.service.BarcodeService;
import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/barcodes")
@ErrorApiResponses
public class BarcodeController {

    private final BarcodeService barcodeService;

    @Operation(summary = "사용자 바코드 목록 조회", description = "로그인한 사용자의 바코드 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "바코드 목록 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BarcodeResponseDto.class)))
    @GetMapping("/user")
    public BaseApiResponse<List<BarcodeResponseDto>> getUserBarcodes() {
        List<BarcodeResponseDto> barcodes = barcodeService.findBarcodesByUserId();
        return BaseApiResponse.ok(200, barcodes, "바코드 목록 조회 성공");
    }
}
