package com.sscanner.team.board.controller;

import com.sscanner.team.board.requestdto.BoardCreateRequestDTO;
import com.sscanner.team.board.requestdto.BoardUpdateRequestDTO;
import com.sscanner.team.board.responsedto.BoardInfoResponseDTO;
import com.sscanner.team.board.responsedto.BoardListResponseDTO;
import com.sscanner.team.board.responsedto.BoardLocationInfoResponseDTO;
import com.sscanner.team.board.responsedto.BoardResponseDTO;
import com.sscanner.team.board.service.BoardService;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.trashcan.type.TrashCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@ErrorApiResponses
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시글 생성", description = "신고 게시글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "신고 게시글 저장 완료!!")
    @PostMapping
    public BaseApiResponse<Void> createAddBoard(@Valid @RequestPart(value = "data") BoardCreateRequestDTO boardCreateRequestDTO,
                                                @RequestPart(value = "files") List<MultipartFile> files) {
        boardService.createBoard(boardCreateRequestDTO, files);
        return BaseApiResponse.ok(201, null, "신고 게시글 저장 완료!!");
    }

    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "신고 게시글 삭제 완료!!")
    @DeleteMapping("/{boardId}")
    public BaseApiResponse<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return BaseApiResponse.ok(200, null, "신고 게시글 삭제 완료!!");
    }

    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "신고 게시글 수정 완료!!")
    @PatchMapping("/{boardId}")
    public BaseApiResponse<Void> updateBoard(@PathVariable Long boardId,
                                             @Valid @RequestPart(value = "data") BoardUpdateRequestDTO boardUpdateRequestDTO,
                                             @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        boardService.updateBoard(boardId, boardUpdateRequestDTO, files);
        return BaseApiResponse.ok(200, null, "신고 게시글 수정 완료!!");
    }

    @Operation(summary = "게시글 목록 조회", description = "카테고리와 쓰레기 분류에 따라 신고 게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "신고 게시글 목록 조회 완료!!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardListResponseDTO.class)))
    @GetMapping
    public BaseApiResponse<BoardListResponseDTO> readAllBoards(
            @RequestParam(value = "board_category", defaultValue = "MODIFY") BoardCategory boardCategory,
            @RequestParam(value = "trash_category", defaultValue = "NORMAL") TrashCategory trashCategory,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        BoardListResponseDTO result = boardService.getBoardList(boardCategory, trashCategory, page, size);
        return BaseApiResponse.ok(200, result, "신고 게시글 목록 조회 완료!!");
    }

    @Operation(summary = "게시글 상세 정보 조회", description = "특정 게시글의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "신고 게시글 상세 정보 조회 완료!!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardResponseDTO.class)))
    @GetMapping("/{boardId}")
    public BaseApiResponse<BoardResponseDTO> readBoard(@PathVariable Long boardId) {
        BoardResponseDTO boardDetailed = boardService.getBoardDetailed(boardId);
        return BaseApiResponse.ok(200, boardDetailed, "신고 게시글 상세 정보 조회 완료!!");
    }

    @Operation(summary = "게시글 위치 정보 조회", description = "특정 게시글의 위치 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "신고 게시글 위치 정보 조회 완료!!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardLocationInfoResponseDTO.class)))
    @GetMapping("/location/{boardId}")
    public BaseApiResponse<BoardLocationInfoResponseDTO> readBoardLocationInfo(@PathVariable Long boardId) {
        BoardLocationInfoResponseDTO boardLocationInfo = boardService.getBoardLocationInfo(boardId);
        return BaseApiResponse.ok(200, boardLocationInfo, "신고 게시글 위치 정보 조회 완료!!");
    }

    @Operation(summary = "내 게시글 목록 조회", description = "로그인한 사용자의 신고 게시글 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "내 신고 게시글 목록 조회 완료!!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoardInfoResponseDTO.class)))
    @GetMapping("/my")
    public BaseApiResponse<List<BoardInfoResponseDTO>> readMyBoards() {
        List<BoardInfoResponseDTO> myBoards = boardService.getMyBoards();
        return BaseApiResponse.ok(200, myBoards, "내 신고 게시글 목록 조회 완료!!");
    }
}
