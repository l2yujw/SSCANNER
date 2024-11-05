package com.sscanner.team.trashcan.responseDto;

import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "쓰레기통 응답 DTO")
public record TrashcanResponseDto(
        @Schema(description = "쓰레기통 ID", example = "1")
        Long id,

        @Schema(description = "위도", example = "37.5665")
        BigDecimal latitude,

        @Schema(description = "경도", example = "126.9780")
        BigDecimal longitude,

        @Schema(description = "도로명 주소", example = "서울특별시 종로구 세종대로 110")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "1층")
        String detailedAddress,

        @Schema(description = "쓰레기 카테고리", example = "GENERAL")
        TrashCategory trashCategory,

        @Schema(description = "쓰레기통 상태", example = "EMPTY")
        TrashcanStatus trashcanStatus
) {
    public static TrashcanResponseDto from(Trashcan trashcan) {
        return new TrashcanResponseDto(
                trashcan.getId(),
                trashcan.getLatitude(),
                trashcan.getLongitude(),
                trashcan.getRoadNameAddress(),
                trashcan.getDetailedAddress(),
                trashcan.getTrashCategory(),
                trashcan.getTrashcanStatus()
        );
    }
}
