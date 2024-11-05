package com.sscanner.team.trashcan.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.trashcan.requestDto.RegisterTrashcanRequestDto;
import com.sscanner.team.trashcan.requestDto.UpdateTrashcanRequestDto;
import com.sscanner.team.trashcan.responseDto.TrashcanResponseDto;
import com.sscanner.team.trashcan.responseDto.TrashcanSearchResponseDto;
import com.sscanner.team.trashcan.responseDto.TrashcanWithImgResponseDto;
import com.sscanner.team.trashcan.service.TrashcanDocumentService;
import com.sscanner.team.trashcan.service.TrashcanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trashcan")
@ErrorApiResponses
public class TrashcanApiController {

    private final TrashcanService trashcanService;
    private final TrashcanDocumentService trashcanDocumentService;

    @Operation(summary = "쓰레기통 등록", description = "새로운 쓰레기통을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "쓰레기통 등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrashcanWithImgResponseDto.class)))
    @PostMapping()
    public BaseApiResponse<TrashcanWithImgResponseDto> registerTrashcan(@RequestPart(value = "data") @Valid RegisterTrashcanRequestDto requestDto,
                                                                        @RequestPart(value = "file") MultipartFile file) {
        TrashcanWithImgResponseDto responseDto = trashcanService.registerTrashcan(requestDto, file);
        return BaseApiResponse.ok(201, responseDto, "쓰레기통 등록 성공");
    }

    @Operation(summary = "쓰레기통 정보 조회", description = "특정 ID의 쓰레기통 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "쓰레기통 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrashcanWithImgResponseDto.class)))
    @GetMapping("/getTrashcan/{trashcanId}")
    public BaseApiResponse<TrashcanWithImgResponseDto> getTrashcanInfo(@PathVariable Long trashcanId) {
        TrashcanWithImgResponseDto responseDto = trashcanService.getTrashcanInfo(trashcanId);
        return BaseApiResponse.ok(200, responseDto, "쓰레기통 조회 성공");
    }

    @Operation(summary = "쓰레기통 검색", description = "도로명 주소를 사용하여 쓰레기통을 검색합니다.")
    @ApiResponse(responseCode = "200", description = "쓰레기통 검색 성공", content = @Content(mediaType = "application/json"))
    @GetMapping("/search")
    public BaseApiResponse<List<TrashcanSearchResponseDto>> searchTrashcans(@RequestParam String word) {
        List<TrashcanSearchResponseDto> responseDtos = trashcanDocumentService.findByRoadNameAddress(word);
        return BaseApiResponse.ok(200, responseDtos, "쓰레기통 검색 성공");
    }

    @Operation(summary = "주변 쓰레기통 조회", description = "입력된 좌표를 기준으로 인근의 쓰레기통 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "쓰레기통 조회 성공", content = @Content(mediaType = "application/json"))
    @GetMapping("/getNearByTrashcans")
    public BaseApiResponse<List<TrashcanResponseDto>> getNearByTrashcan(
            @NotNull(message = "위도는 필수입니다.")
            @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다.")
            @DecimalMax(value = "90.0", message = "위도는 90 이하이어야 합니다.")
            @RequestParam BigDecimal latitude,

            @NotNull(message = "경도는 필수입니다.")
            @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다.")
            @DecimalMax(value = "180.0", message = "경도는 180 이하이어야 합니다.")
            @RequestParam BigDecimal longitude) {
        List<TrashcanResponseDto> responseDtos = trashcanService.getTrashcanByCoordinate(latitude, longitude);
        return BaseApiResponse.ok(200, responseDtos, "쓰레기통 조회 성공");
    }

    @Operation(summary = "쓰레기통 정보 수정", description = "특정 ID의 쓰레기통 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "쓰레기통 정보 변경 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrashcanResponseDto.class)))
    @PutMapping("/{trashcanId}")
    public BaseApiResponse<TrashcanResponseDto> updateProductInfo(@RequestBody @Valid UpdateTrashcanRequestDto requestDto, @PathVariable Long trashcanId) {
        TrashcanResponseDto responseDto = trashcanService.updateTrashcanInfo(trashcanId, requestDto);
        return BaseApiResponse.ok(200, responseDto, "쓰레기통 정보 변경 성공");
    }

    @Operation(summary = "쓰레기통 정보 삭제", description = "특정 ID의 쓰레기통 정보를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "쓰레기통 정보 삭제 성공")
    @DeleteMapping("/{trashcanId}")
    public BaseApiResponse<?> deleteTrashcanInfo(@PathVariable Long trashcanId) {
        trashcanService.deleteTrashcanInfo(trashcanId);
        return BaseApiResponse.ok(200, "쓰레기통 정보 삭제 성공");
    }
}
