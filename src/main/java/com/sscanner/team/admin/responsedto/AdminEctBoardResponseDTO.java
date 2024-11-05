package com.sscanner.team.admin.responsedto;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.entity.BoardImg;
import com.sscanner.team.board.responsedto.BoardImgResponseDTO;
import com.sscanner.team.board.type.ApprovalStatus;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "관리자 기타 게시글 상세 응답 DTO")
public record AdminEctBoardResponseDTO(

        @Schema(description = "게시글 카테고리", example = "REGISTER")
        BoardCategory boardCategory,

        @Schema(description = "쓰레기 카테고리", example = "NORMAL")
        TrashCategory trashCategory,

        @Schema(description = "게시글 이미지 리스트")
        List<BoardImgResponseDTO> images,

        @Schema(description = "쓰레기통 상태", example = "AVAILABLE")
        TrashcanStatus trashcanStatus,

        @Schema(description = "중요 정보", example = "쓰레기통 위치 업데이트")
        String significant,

        ApprovalStatus approvalStatus
) {
    public static AdminEctBoardResponseDTO of(Board board, List<BoardImg> boardImgs) {
        return new AdminEctBoardResponseDTO(
                board.getBoardCategory(),
                board.getTrashCategory(),
                boardImgs.stream()
                        .map(BoardImgResponseDTO::from)
                        .toList(),
                board.getUpdatedTrashcanStatus(),
                board.getSignificant(),
                board.getApprovalStatus()
        );
    }
}
