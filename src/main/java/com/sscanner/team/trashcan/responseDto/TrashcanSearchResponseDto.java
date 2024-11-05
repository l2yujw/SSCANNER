package com.sscanner.team.trashcan.responseDto;

import com.sscanner.team.trashcan.entity.TrashcanDocument;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "쓰레기통 검색 응답 DTO")
public record TrashcanSearchResponseDto(
        @Schema(description = "쓰레기통 ID", example = "1")
        Long id,

        @Schema(description = "위도", example = "37.5665")
        BigDecimal latitude,

        @Schema(description = "경도", example = "126.9780")
        BigDecimal longitude
) {
    public static TrashcanSearchResponseDto from(TrashcanDocument trashcanDocument) {
        return new TrashcanSearchResponseDto(
                trashcanDocument.getId(),
                trashcanDocument.getLatitude(),
                trashcanDocument.getLongitude()
        );
    }
}
