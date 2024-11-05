package com.sscanner.team.admin.controller;

import com.sscanner.team.admin.requestdto.AdminBoardRequestDTO;
import com.sscanner.team.admin.responsedto.AdminBoardListResponseDTO;
import com.sscanner.team.admin.responsedto.AdminEctBoardResponseDTO;
import com.sscanner.team.admin.responsedto.AdminModifyBoardResponseDTO;
import com.sscanner.team.admin.service.AdminService;
import com.sscanner.team.board.type.ApprovalStatus;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.points.dto.requestdto.PointRequestDto;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@ErrorApiResponses
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "관리자 게시글 리스트 조회",
            description = "승인 상태, 게시글 카테고리, 쓰레기 카테고리로 필터링하여 게시글 목록을 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminBoardListResponseDTO.class)))
    @GetMapping("/boards")
    public BaseApiResponse<AdminBoardListResponseDTO> readAllBoards(
            @RequestParam(value = "approval_status", defaultValue = "REVIEWING") ApprovalStatus approvalStatus,
            @RequestParam(value = "board_category", defaultValue = "MODIFY") BoardCategory boardCategory,
            @RequestParam(value = "trash_category", defaultValue = "NORMAL") TrashCategory trashCategory,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "6") Integer size
    ) {
        AdminBoardListResponseDTO boards = adminService.getBoards(approvalStatus, trashCategory, boardCategory, page, size);
        return BaseApiResponse.ok(200, boards, "관리자 게시글 목록 조회 완료!!");
    }

    @Operation(
            summary = "관리자 수정 신고 게시글 상세 조회",
            description = "특정 수정 신고 게시글의 상세 정보를 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminModifyBoardResponseDTO.class)))
    @GetMapping("/boards/modify/{boardId}")
    public BaseApiResponse<AdminModifyBoardResponseDTO> readModifyBoardDetailed(@PathVariable Long boardId) {
        AdminModifyBoardResponseDTO modifyBoard = adminService.getModifyBoard(boardId);
        return BaseApiResponse.ok(200, modifyBoard, "관리자 수정 신고 게시글 상세 조회 완료!!");
    }

    @Operation(
            summary = "관리자 기타 게시글 상세 조회",
            description = "특정 기타 게시글의 상세 정보를 조회합니다."
    )
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdminEctBoardResponseDTO.class)))
    @GetMapping("/boards/ect/{boardId}")
    public BaseApiResponse<AdminEctBoardResponseDTO> readEctBoardDetailed(@PathVariable Long boardId) {
        AdminEctBoardResponseDTO ectBoard = adminService.getEctBoard(boardId);
        return BaseApiResponse.ok(200, ectBoard, "어드민 등록 및 삭제 신고 게시글 상세 조회 완료!!");
    }

    @Operation(
            summary = "관리자 신고 게시글 반영",
            description = "특정 게시글을 반영하여 수정합니다."
    )
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.",
            content = @Content(mediaType = "application/json"))
    @PatchMapping("/boards/{boardId}")
    public BaseApiResponse<?> reflectBoard(
            @PathVariable Long boardId,
            @Valid @RequestBody AdminBoardRequestDTO adminBoardRequestDTO
    ) {
        adminService.reflectBoard(boardId, adminBoardRequestDTO);
        return BaseApiResponse.ok(200, "관리자 신고 게시글 반영 완료!!");
    }

    @Operation(
            summary = "포인트 지급",
            description = "특정 게시글 작성자에게 포인트를 지급합니다."
    )
    @PostMapping("/points/{boardId}")
    public BaseApiResponse<?> givePoints(
            @PathVariable Long boardId,
            @Valid @RequestBody PointRequestDto pointRequestDto
    ) {
        adminService.givePoints(boardId, pointRequestDto);
        return BaseApiResponse.ok(200, "포인트 지급 완료!!");
    }
}
