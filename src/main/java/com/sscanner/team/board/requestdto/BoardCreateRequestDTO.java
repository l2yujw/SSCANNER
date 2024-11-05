package com.sscanner.team.board.requestdto;

import com.sscanner.team.user.entity.User;
import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public record BoardCreateRequestDTO(
        @NotNull(message = "게시판 유형 작성은 필수입니다.")
        @Schema(description = "게시판 유형", example = "MODIFY")
        BoardCategory boardCategory,

        @Schema(description = "중요한 내용", example = "특정 정보 설명")
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
    public Board toEntityBoard(User user) {
        return Board.builder()
                .user(user)
                .boardCategory(this.boardCategory())
                .significant(this.significant())
                .trashcanId(this.trashcanId())
                .latitude(this.latitude())
                .longitude(this.longitude())
                .roadNameAddress(this.roadNameAddress())
                .detailedAddress(this.detailedAddress())
                .trashCategory(this.trashCategory())
                .updatedTrashcanStatus(this.updatedTrashcanStatus())
                .build();
    }
}
