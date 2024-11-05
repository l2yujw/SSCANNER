package com.sscanner.team.points.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.points.dto.responsedto.PointWithUserIdResponseDto;
import com.sscanner.team.points.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
@ErrorApiResponses
public class PointController {

    private final PointService pointService;

    @Operation(summary = "사용자 포인트 조회", description = "로그인한 사용자의 포인트를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 포인트 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointWithUserIdResponseDto.class)))
    @GetMapping("/user")
    public BaseApiResponse<PointWithUserIdResponseDto> getUserPoints() {
        PointWithUserIdResponseDto pointWithUserIdResponseDto = pointService.getCachedPoint();
        return BaseApiResponse.ok(200, pointWithUserIdResponseDto, "사용자 포인트 조회 성공");
    }
}
