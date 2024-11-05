package com.sscanner.team.trashcan.requestDto;

import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "쓰레기통 등록 요청 DTO")
public record RegisterTrashcanRequestDto(

        @Schema(description = "위도 (-90 ~ 90)", example = "37.5665")
        @NotNull(message = "위도는 필수입니다.")
        @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다.")
        @DecimalMax(value = "90.0", message = "위도는 90 이하이어야 합니다.")
        BigDecimal latitude,

        @Schema(description = "경도 (-180 ~ 180)", example = "126.9780")
        @NotNull(message = "경도는 필수입니다.")
        @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다.")
        @DecimalMax(value = "180.0", message = "경도는 180 이하이어야 합니다.")
        BigDecimal longitude,

        @Schema(description = "도로명 주소", example = "서울특별시 종로구 세종대로 110")
        @NotBlank(message = "도로명 주소는 필수입니다.")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "1층")
        String detailedAddress,

        @Schema(description = "쓰레기 카테고리", example = "GENERAL")
        @NotNull(message = "카테고리는 필수입니다.")
        TrashCategory trashCategory

) {
    public Trashcan toEntity() {
        return Trashcan.builder()
                .latitude(this.latitude())
                .longitude(this.longitude())
                .roadNameAddress(this.roadNameAddress())
                .detailedAddress(this.detailedAddress())
                .trashCategory(this.trashCategory())
                .build();
    }
}
