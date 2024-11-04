package com.sscanner.team.comment.responsedto;

import com.sscanner.team.user.entity.User;
import com.sscanner.team.comment.entity.Comment;

public record CommentResponseDTO(
        Long id,
        String nickname,
        String authority,
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
