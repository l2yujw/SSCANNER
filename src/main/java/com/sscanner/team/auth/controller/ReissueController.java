package com.sscanner.team.auth.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.auth.responsedto.RefreshResponseDto;
import com.sscanner.team.auth.service.ReissueServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ErrorApiResponses
public class ReissueController {

    private final ReissueServiceImpl reissueServiceImpl;

    @Operation(summary = "토큰 재발급", description = "클라이언트 요청을 통해 새로운 액세스 및 리프레시 토큰을 재발급합니다.")
    @ApiResponse(responseCode = "200", description = "토큰 재발급 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshResponseDto.class)))
    @PostMapping("/reissue")
    public BaseApiResponse<RefreshResponseDto> reissue(HttpServletRequest request, HttpServletResponse response) {
        reissueServiceImpl.reissueToken(request, response);
        return BaseApiResponse.ok(201, null, "토큰 재발급 성공");
    }
}
