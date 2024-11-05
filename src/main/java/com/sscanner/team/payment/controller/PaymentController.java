package com.sscanner.team.payment.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.payment.requestdto.PointPaymentRequestDto;
import com.sscanner.team.payment.responsedto.PointPaymentResponseDto;
import com.sscanner.team.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@ErrorApiResponses
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "포인트 결제 처리", description = "사용자의 포인트를 사용하여 결제를 처리합니다.")
    @ApiResponse(responseCode = "201", description = "포인트가 성공적으로 사용되었습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointPaymentResponseDto.class)))
    @PostMapping
    public BaseApiResponse<PointPaymentResponseDto> payUserPoints(@RequestBody PointPaymentRequestDto pointPaymentRequestDto) {
        PointPaymentResponseDto response = paymentService.processPointPayment(pointPaymentRequestDto);
        return BaseApiResponse.ok(201, response, "포인트가 성공적으로 사용되었습니다.");
    }
}
