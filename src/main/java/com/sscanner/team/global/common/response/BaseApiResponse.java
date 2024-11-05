package com.sscanner.team.global.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Schema(description = "API 응답 구조")
public class BaseApiResponse<T> {

    @Schema(description = "응답 상태 코드", example = "200")
    private int code;

    @Schema(description = "응답 메시지", example = "요청이 성공적으로 처리되었습니다.")
    private String message;

    private T data;

    public static <T> BaseApiResponse<T> ok(int status, T data, String message) {
        return new BaseApiResponse<>(status, message, data);
    }

    public static <T> BaseApiResponse<T> ok(T data, String message) {
        return new BaseApiResponse<>(200, message, data);
    }

    public static BaseApiResponse<?> ok(String message) {
        return new BaseApiResponse<>(200, message, null);
    }

    public static BaseApiResponse<?> error(int code, String message) {
        return new BaseApiResponse<>(code, message, null);
    }

    public BaseApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
