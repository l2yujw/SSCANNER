package com.sscanner.team.admin.responsedto;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.entity.BoardImg;
import com.sscanner.team.board.type.ApprovalStatus;
import com.sscanner.team.trashcan.entity.Trashcan;
import com.sscanner.team.trashcan.entity.TrashcanImg;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "관리자 수정 신고 게시글 상세 응답 DTO")
public record AdminModifyBoardResponseDTO(

        @Schema(description = "쓰레기 카테고리", example = "RECYCLABLE")
        TrashCategory trashCategory,

        @Schema(description = "쓰레기통 이미지 URL", example = "https://example.com/trashcan-image.jpg")
        String trashcanImgUrl,

        @Schema(description = "쓰레기통 상태", example = "FULL")
        TrashcanStatus trashcanStatus,

        @Schema(description = "게시글 이미지 URL 리스트")
        List<String> boardImgUrls,

        @Schema(description = "수정된 쓰레기통 상태", example = "EMPTY")
        TrashcanStatus updatedTrashcanStatus,

        @Schema(description = "중요 정보", example = "위치 변경 및 상태 업데이트")
        String significant,

        ApprovalStatus approvalStatus
) {
    public static AdminModifyBoardResponseDTO of(Trashcan trashcan, TrashcanImg trashcanImg,
                                                   Board board, List<BoardImg> boardImgs) {
        return new AdminModifyBoardResponseDTO(
                trashcan.getTrashCategory(),
                trashcanImg.getTrashcanImgUrl(),
                trashcan.getTrashcanStatus(),
                boardImgs.stream().map(BoardImg::getBoardImgUrl).toList(),
                board.getUpdatedTrashcanStatus(),
                board.getSignificant(),
                board.getApprovalStatus()
        );
    }
}
