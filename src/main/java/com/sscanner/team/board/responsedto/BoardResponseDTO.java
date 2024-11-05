package com.sscanner.team.board.responsedto;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.entity.BoardImg;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.trashcan.type.TrashcanStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "게시글 응답 DTO")
public record BoardResponseDTO(
        @Schema(description = "게시글 ID", example = "1")
        Long id,

        @Schema(description = "도로명 주소", example = "서울특별시 중구 세종대로 110")
        String roadNameAddress,

        @Schema(description = "상세 주소", example = "광화문빌딩 20층")
        String detailedAddress,

        @Schema(description = "게시글 카테고리", example = "MODIFY")
        BoardCategory boardCategory,

        @Schema(description = "게시글의 중요 내용", example = "중요 설명")
        String significant,

        @Schema(description = "관련 쓰레기통 ID", example = "123")
        Long trashcanId,

        @Schema(description = "쓰레기 카테고리", example = "NORMAL")
        TrashCategory trashCategory,

        @Schema(description = "변경된 쓰레기통 상태", example = "EMPTY")
        TrashcanStatus updatedTrashcanStatus,

        @Schema(description = "게시글 이미지 목록")
        List<BoardImgResponseDTO> boardImgs,
        
        boolean isAuthor
) {
    public static BoardResponseDTO of(Board board, List<BoardImg> boardImgs, boolean isAuthor) {
        return new BoardResponseDTO(
                board.getId(),
                board.getRoadNameAddress(),
                board.getDetailedAddress(),
                board.getBoardCategory(),
                board.getSignificant(),
                board.getTrashcanId(),
                board.getTrashCategory(),
                board.getUpdatedTrashcanStatus(),
                boardImgs.stream()
                        .map(BoardImgResponseDTO::from)
                        .collect(Collectors.toList()),
                isAuthor
        );
    }
}
