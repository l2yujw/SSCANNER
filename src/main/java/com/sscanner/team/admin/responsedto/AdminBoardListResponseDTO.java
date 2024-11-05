package com.sscanner.team.admin.responsedto;

import com.sscanner.team.board.type.ApprovalStatus;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

@Schema(description = "관리자 게시글 목록 응답 DTO")
public record AdminBoardListResponseDTO(

        @Schema(description = "승인 상태 필터", example = "APPROVED")
        ApprovalStatus approvalStatus,

        @Schema(description = "쓰레기 카테고리 필터", example = "NORMAL")
        TrashCategory trashCategory,

        @Schema(description = "게시글 카테고리 필터", example = "MODIFY")
        BoardCategory boardCategory,

        @Schema(description = "게시글 정보 페이지")
        Page<AdminBoardInfoResponseDTO> boardList
) {
    public static AdminBoardListResponseDTO of(ApprovalStatus approvalStatus,
                                               TrashCategory trashCategory,
                                               BoardCategory boardCategory,
                                               Page<AdminBoardInfoResponseDTO> boards) {
        return new AdminBoardListResponseDTO(
                approvalStatus,
                trashCategory,
                boardCategory,
                boards
        );
    }
}
