package com.sscanner.team.points.controller;

import com.sscanner.team.global.common.response.ApiResponse;
import com.sscanner.team.points.dto.requestdto.PointRequestDto;
import com.sscanner.team.points.dto.responsedto.PointWithUserIdResponseDto;
import com.sscanner.team.points.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    @GetMapping("/user")
    public ApiResponse<PointWithUserIdResponseDto> getUserPoints() {
        PointWithUserIdResponseDto pointWithUserIdResponseDto = pointService.getCachedPoint();
        return ApiResponse.ok(200, pointWithUserIdResponseDto, "사용자 포인트 조회 성공");
    }
}
