package com.sscanner.team.comment.requestdto;

import com.sscanner.team.user.entity.User;
import com.sscanner.team.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "댓글 생성 요청 DTO")
public record CommentCreateRequestDTO(
        @Schema(description = "게시글 ID", example = "1")
        @NotNull(message = "boardId 작성은 필수입니다.")
        Long boardId,

        @Schema(description = "댓글 내용", example = "이 댓글은 정말 유익해요!")
        @NotBlank(message = "내용 작성은 필수입니다.")
        String content
) {
    public Comment toEntityComment(User user) {
        return Comment.builder()
                .boardId(boardId)
                .user(user)
                .content(content)
                .build();
    }
}
