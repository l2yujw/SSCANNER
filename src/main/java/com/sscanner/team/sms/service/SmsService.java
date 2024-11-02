package com.sscanner.team.sms.service;

import com.sscanner.team.sms.requestdto.SmsRequestDto;
import com.sscanner.team.sms.requestdto.SmsVerifyRequestDto;

public interface SmsService {

    // 가입되지 않은 사용자에게 SMS 전송
    void sendSmsForUnregisteredUser(SmsRequestDto smsRequestDto);

    // 가입된 사용자에게 SMS 전송
    void sendSmsForRegisteredUser(SmsRequestDto smsRequestDto);

    // 인증 코드를 검증하는 메서드
    boolean verifyCode(SmsVerifyRequestDto smsVerifyDto);

    // 인증코드 전화번호 검증
    boolean isVerify(String phoneNum, String code);
}
