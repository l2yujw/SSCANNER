package com.sscanner.team.gifticon.controller;

import com.sscanner.team.gifticon.responsedto.GifticonResponseDto;
import com.sscanner.team.gifticon.service.GifticonService;
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
@RequestMapping("/api/gifticons")
@ErrorApiResponses
public class GifticonController {

    private final GifticonService gifticonService;

    @Operation(summary = "사용자 기프티콘 목록 조회", description = "로그인한 사용자의 기프티콘 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "기프티콘 목록 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GifticonResponseDto.class)))
    @GetMapping("/user")
    public BaseApiResponse<List<GifticonResponseDto>> getUserGifticons() {
        List<GifticonResponseDto> gifticons = gifticonService.getGifticonsByUserId();
        return BaseApiResponse.ok(200, gifticons, "기프티콘 목록 조회 성공");
    }
}
