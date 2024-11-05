package com.sscanner.team.comment.responsedto;

import com.sscanner.team.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "댓글 응답 DTO")
public record CommentResponseDTO(
        @Schema(description = "댓글 ID", example = "1")
        Long id,

        @Schema(description = "작성자 닉네임", example = "john_doe")
        String nickname,

        @Schema(description = "작성자 권한", example = "USER")
        String authority,

        @Schema(description = "댓글 내용", example = "이 글 정말 좋습니다.")
        String content,

        boolean isAuthor
) {
    public static CommentResponseDTO of(Comment comment, boolean isAuthor) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getUser().getAuthority(),
                comment.getContent(),
                isAuthor
        );
    }
}
