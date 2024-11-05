package com.sscanner.team.comment.controller;

import com.sscanner.team.comment.requestdto.CommentCreateRequestDTO;
import com.sscanner.team.comment.responsedto.CommentResponseDTO;
import com.sscanner.team.comment.service.CommentService;
import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@ErrorApiResponses
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "새로운 댓글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "댓글 생성 완료!!")
    @PostMapping
    public BaseApiResponse<Void> createComment(@Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO) {
        commentService.saveComment(commentCreateRequestDTO);
        return BaseApiResponse.ok(201, null, "댓글 생성 완료!!");
    }

    @Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 완료!!")
    @DeleteMapping("/{commentId}")
    public BaseApiResponse<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return BaseApiResponse.ok(200, null, "댓글 삭제 완료!!");
    }

    @Operation(summary = "게시글의 댓글 조회", description = "특정 게시글에 대한 모든 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 조회 완료!!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.class)))
    @GetMapping("/{boardId}")
    public BaseApiResponse<List<CommentResponseDTO>> getComments(@PathVariable Long boardId) {
        List<CommentResponseDTO> comments = commentService.getComments(boardId);
        return BaseApiResponse.ok(200, comments, "댓글 조회 완료!!");
    }

    @Operation(summary = "게시글의 모든 댓글 삭제", description = "특정 게시글에 달린 모든 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "모든 댓글 삭제 완료!!")
    @DeleteMapping("/all/{boardId}")
    public BaseApiResponse<Void> deleteAllComments(@PathVariable Long boardId) {
        commentService.deleteAll(boardId);
        return BaseApiResponse.ok(200, null, "모든 댓글 삭제 완료!!");
    }
}
