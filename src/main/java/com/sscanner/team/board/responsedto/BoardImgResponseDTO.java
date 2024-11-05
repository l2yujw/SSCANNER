package com.sscanner.team.board.responsedto;

import com.sscanner.team.board.entity.BoardImg;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 이미지 응답 DTO")
public record BoardImgResponseDTO(
        @Schema(description = "게시글 이미지 URL", example = "https://example.com/image.jpg")
        String boardImgUrl
) {
    public static BoardImgResponseDTO from(BoardImg boardImg) {
        return new BoardImgResponseDTO(
                boardImg.getBoardImgUrl());
    }
}
