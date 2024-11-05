package com.sscanner.team.board.responsedto;

import com.sscanner.team.board.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 기본 정보 응답 DTO")
public record BoardInfoResponseDTO(
        @Schema(description = "게시글 ID", example = "1")
        Long id,

        @Schema(description = "게시글 첫 번째 이미지 URL", example = "https://example.com/image1.jpg")
        String boardFirstImgUrl,

        @Schema(description = "도로명 주소", example = "서울특별시 중구 세종대로 110")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "광화문빌딩 20층")
        String detailedAddress
) {
    public static BoardInfoResponseDTO of(Board board, String boardFirstImgUrl) {
        return new BoardInfoResponseDTO(
                board.getId(),
                boardFirstImgUrl,
                board.getRoadNameAddress(),
                board.getDetailedAddress()
        );
    }
}
