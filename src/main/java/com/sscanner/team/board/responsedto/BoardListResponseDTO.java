package com.sscanner.team.board.responsedto;

import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

@Schema(description = "게시글 목록 응답 DTO")
public record BoardListResponseDTO(
        @Schema(description = "게시글 카테고리", example = "MODIFY")
        BoardCategory boardCategory,

        @Schema(description = "쓰레기 카테고리", example = "NORMAL")
        TrashCategory trashCategory,

        @Schema(description = "게시글 정보 목록 페이지", example = "Page 객체")
        Page<BoardInfoResponseDTO> boardList
) {
    public static BoardListResponseDTO from(BoardCategory boardCategory,
                                            TrashCategory trashCategory,
                                            Page<BoardInfoResponseDTO> boardList) {
        return new BoardListResponseDTO(
                boardCategory,
                trashCategory,
                boardList
        );
    }
}
