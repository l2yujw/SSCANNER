package com.sscanner.team.sms.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.global.exception.BadRequestException;
import com.sscanner.team.global.exception.ExceptionCode;
import com.sscanner.team.sms.requestdto.SmsRequestDto;
import com.sscanner.team.sms.requestdto.SmsVerifyRequestDto;
import com.sscanner.team.sms.service.SmsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sms")
@ErrorApiResponses
public class SmsController {

    private final SmsServiceImpl smsServiceImpl;

    @Operation(summary = "미등록 사용자에게 문자 전송", description = "미등록 사용자에게 인증 문자를 전송합니다.")
    @ApiResponse(responseCode = "200", description = "문자를 전송했습니다.")
    @PostMapping("/send-for-unregistered")
    public BaseApiResponse<Void> sendSmsForUnregisteredUser(@RequestBody @Valid SmsRequestDto smsRequestDto) {
        smsServiceImpl.sendSmsForUnregisteredUser(smsRequestDto);
        return new BaseApiResponse<>(200, "문자를 전송했습니다", null);
    }

    @Operation(summary = "등록 사용자에게 문자 전송", description = "등록 사용자에게 인증 문자를 전송합니다.")
    @ApiResponse(responseCode = "200", description = "문자를 전송했습니다.")
    @PostMapping("/send-for-registered")
    public BaseApiResponse<Void> sendSmsForRegisteredUser(@RequestBody @Valid SmsRequestDto smsRequestDto) {
        smsServiceImpl.sendSmsForRegisteredUser(smsRequestDto);
        return new BaseApiResponse<>(200, "문자를 전송했습니다", null);
    }

    @Operation(summary = "인증 코드 검증", description = "사용자가 입력한 인증 코드를 검증합니다.")
    @ApiResponse(responseCode = "200", description = "인증이 완료되었습니다.")
    @PostMapping("/verify")
    public BaseApiResponse<Void> verifyCode(@RequestBody @Valid SmsVerifyRequestDto req) {
        boolean verify = smsServiceImpl.verifyCode(req);
        if (verify) {
            return new BaseApiResponse<>(200, "인증이 완료되었습니다.", null);
        } else {
            throw new BadRequestException(ExceptionCode.UNAUTHORIZED);
        }
    }
}
