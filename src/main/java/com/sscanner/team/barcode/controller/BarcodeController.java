package com.sscanner.team.barcode.controller;

import com.sscanner.team.barcode.responsedto.BarcodeResponseDto;
import com.sscanner.team.barcode.service.BarcodeService;
import com.sscanner.team.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/barcodes")
public class BarcodeController {

    private final BarcodeService barcodeService;

    @GetMapping("/user")
    public ApiResponse<List<BarcodeResponseDto>> getUserBarcodes() {
        List<BarcodeResponseDto> barcodes = barcodeService.findBarcodesByUserId();
        return ApiResponse.ok(200, barcodes, "바코드 목록 조회 성공");
    }
}
