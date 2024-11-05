package com.sscanner.team.board.responsedto;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "게시글 위치 정보 응답 DTO")
public record BoardLocationInfoResponseDTO(
        @Schema(description = "게시글 ID", example = "1")
        Long id,

        @Schema(description = "쓰레기 카테고리", example = "NORMAL")
        TrashCategory trashCategory,

        @Schema(description = "위도 값", example = "37.5665")
        BigDecimal latitude,

        @Schema(description = "경도 값", example = "126.9780")
        BigDecimal longitude,

        @Schema(description = "도로명 주소", example = "서울특별시 중구 세종대로 110")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "광화문빌딩 20층")
        String detailedAddress
) {
    public static BoardLocationInfoResponseDTO from(Board board) {
        return new BoardLocationInfoResponseDTO(
                board.getId(),
                board.getTrashCategory(),
                board.getLatitude(),
                board.getLongitude(),
                board.getRoadNameAddress(),
                board.getDetailedAddress()
        );
    }
}
