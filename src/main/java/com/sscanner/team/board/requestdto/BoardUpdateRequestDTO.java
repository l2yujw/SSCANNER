package com.sscanner.team.board.requestdto;

import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "게시글 수정 요청 DTO")
public record BoardUpdateRequestDTO(
        @Schema(description = "중요한 정보", example = "특정 설명 내용")
        String significant,

        @Schema(description = "관련 쓰레기통 ID", example = "123")
        Long trashcanId,

        @NotNull(message = "위도 작성은 필수입니다.")
        @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다.")
        @DecimalMax(value = "90.0", message = "위도는 90 이하이어야 합니다.")
        @Schema(description = "위도 값", example = "37.5665")
        BigDecimal latitude,

        @NotNull(message = "경도 작성은 필수입니다.")
        @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다.")
        @DecimalMax(value = "180.0", message = "경도는 180 이하이어야 합니다.")
        @Schema(description = "경도 값", example = "126.9780")
        BigDecimal longitude,

        @NotBlank(message = "도로명 주소 작성은 필수입니다.")
        @Schema(description = "도로명 주소", example = "서울특별시 중구 세종대로 110")
        String roadNameAddress,

        @NotBlank(message = "상세 주소 작성은 필수입니다.")
        @Schema(description = "상세 주소", example = "광화문빌딩 20층")
        String detailedAddress,

        @NotNull(message = "쓰레기통 유형 작성은 필수입니다.")
        @Schema(description = "쓰레기통 유형", example = "NORMAL")
        TrashCategory trashCategory,

        @NotNull(message = "쓰레기통 상태 작성은 필수입니다.")
        @Schema(description = "변경된 쓰레기통 상태", example = "EMPTY")
        TrashcanStatus updatedTrashcanStatus
) {
}
