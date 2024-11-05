package com.sscanner.team.auth.service;

import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.auth.responsedto.RefreshResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReissueService {
    BaseApiResponse<RefreshResponseDto> reissueToken(HttpServletRequest request, HttpServletResponse response);
}
