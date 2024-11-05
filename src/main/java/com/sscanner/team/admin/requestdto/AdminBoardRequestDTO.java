package com.sscanner.team.admin.requestdto;

import com.sscanner.team.board.type.ApprovalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "관리자 게시글 반영 요청 DTO")
public record AdminBoardRequestDTO(

        @Schema(description = "선택된 이미지의 URL", example = "https://example.com/image.jpg")
        @NotNull(message = "사진 선택은 필수입니다.")
        String chosenImgUrl,

        @Schema(description = "승인 상태 (APPROVED, REJECTED)", example = "APPROVED")
        @NotNull(message = "승인 선택은 필수입니다. 승인, 거절 중 골라주세요")
        ApprovalStatus approvalStatus
) {}
