package com.sscanner.team.user.controller;

import com.sscanner.team.global.annotation.ErrorApiResponses;
import com.sscanner.team.global.common.response.BaseApiResponse;
import com.sscanner.team.global.exception.BadRequestException;
import com.sscanner.team.global.exception.ExceptionCode;
import com.sscanner.team.user.requestdto.*;
import com.sscanner.team.user.responsedto.*;
import com.sscanner.team.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@ErrorApiResponses
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Operation(summary = "회원가입", description = "새로운 사용자를 회원가입시킵니다.")
    @ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserJoinResponseDto.class)))
    @PostMapping("/join")
    public BaseApiResponse<UserJoinResponseDto> registerUser(@RequestBody @Valid UserJoinRequestDto requestDTO) {
        userServiceImpl.join(requestDTO);
        return BaseApiResponse.ok(201, null, "회원가입 성공");
    }

    @Operation(summary = "마이페이지 조회", description = "로그인한 사용자의 마이페이지 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "마이페이지 정보 조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMypageResponseDto.class)))
    @GetMapping("/my-page")
    public BaseApiResponse<UserMypageResponseDto> getMypage() {
        return userServiceImpl.getMypage();
    }

    @Operation(summary = "닉네임 수정", description = "사용자의 닉네임을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "닉네임 수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserNicknameUpdateResponseDto.class)))
    @PatchMapping("/change-nickname")
    public BaseApiResponse<UserNicknameUpdateResponseDto> updateNickname(@RequestBody UserNicknameUpdateRequestDto requestDto) {
        UserNicknameUpdateResponseDto responseDto = userServiceImpl.updateNickname(requestDto.newNickname());
        return BaseApiResponse.ok(200, responseDto, "닉네임 수정 성공");
    }

    @Operation(summary = "비밀번호 확인", description = "현재 비밀번호를 확인하여 핸드폰 번호 수정 전 접근 권한을 확인합니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호 확인 성공")
    @PostMapping("/confirm-password")
    public BaseApiResponse<String> confirmPassword(@RequestBody String password) {
        boolean isConfirmed = userServiceImpl.confirmPassword(password);
        if (isConfirmed) {
            return BaseApiResponse.ok(200, null, "비밀번호 확인 성공");
        } else {
            throw new BadRequestException(ExceptionCode.CURRENT_PASSWORD_NOT_MATCH);
        }
    }

    @Operation(summary = "핸드폰 번호 수정", description = "사용자의 핸드폰 번호를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "핸드폰 번호 수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPhoneUpdateResponseDto.class)))
    @PatchMapping("/change-phone")
    public BaseApiResponse<UserPhoneUpdateResponseDto> updatePhoneNumber(@Valid @RequestBody UserPhoneUpdateRequestDto requestDto) {
        UserPhoneUpdateResponseDto responseDto = userServiceImpl.updatePhoneNumber(requestDto);
        return BaseApiResponse.ok(responseDto, "핸드폰 번호가 수정되었습니다.");
    }

    @Operation(summary = "비밀번호 수정", description = "사용자의 비밀번호를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    @PatchMapping("/change-password")
    public BaseApiResponse<String> changePassword(@RequestBody UserPasswordChangeRequestDto requestDto) {
        String message = userServiceImpl.changePassword(requestDto);
        return BaseApiResponse.ok(message, "비밀번호 수정 성공");
    }

    @Operation(summary = "회원 탈퇴", description = "사용자가 자신의 계정을 탈퇴합니다.")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    @DeleteMapping("/delete")
    public BaseApiResponse<Void> deleteUser() {
        userServiceImpl.deleteUser();
        return BaseApiResponse.ok(200, null, "회원 탈퇴 성공");
    }

    @Operation(summary = "아이디 찾기", description = "사용자가 아이디를 찾습니다.")
    @ApiResponse(responseCode = "200", description = "아이디 찾기 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserFindIdResponseDto.class)))
    @PostMapping("/find-id")
    public BaseApiResponse<UserFindIdResponseDto> findId(@Valid @RequestBody UserFindIdRequestDto requestDto) {
        UserFindIdResponseDto responseDto = userServiceImpl.findUserId(requestDto);
        return BaseApiResponse.ok(200, responseDto, "아이디 찾기 성공");
    }

    @Operation(summary = "비밀번호 재설정", description = "사용자의 비밀번호를 재설정합니다.")
    @ApiResponse(responseCode = "200", description = "비밀번호가 성공적으로 변경되었습니다.")
    @PostMapping("/reset-password")
    public BaseApiResponse<String> resetPassword(@RequestBody UserResetPasswordRequestDto requestDto) {
        userServiceImpl.resetPassword(requestDto);
        return BaseApiResponse.ok(200, null, "비밀번호가 성공적으로 변경되었습니다.");
    }
}
