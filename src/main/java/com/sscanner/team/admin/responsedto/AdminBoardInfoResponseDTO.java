package com.sscanner.team.admin.responsedto;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.type.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "관리자 게시글 정보 응답 DTO")
public record AdminBoardInfoResponseDTO(

        @Schema(description = "게시글 ID", example = "1")
        Long id,

        @Schema(description = "게시글의 첫 번째 이미지 URL", example = "https://example.com/board-image.jpg")
        String boardFirstImgUrl,

        @Schema(description = "도로명 주소", example = "서울특별시 강남구 테헤란로 123")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "3층 301호")
        String detailedAddress,

        @Schema(description = "승인 상태", example = "APPROVED")
        ApprovalStatus approvalStatus
) {
    public static AdminBoardInfoResponseDTO of(Board board, String boardFirstImgUrl) {
        return new AdminBoardInfoResponseDTO(
                board.getId(),
                boardFirstImgUrl,
                board.getRoadNameAddress(),
                board.getDetailedAddress(),
                board.getApprovalStatus()
        );
    }
}
