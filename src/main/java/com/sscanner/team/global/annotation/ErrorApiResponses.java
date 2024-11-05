package com.sscanner.team.global.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(example = "{\"code\": 400, \"message\": \"잘못된 요청입니다.\", \"data\": null}"))),
        @ApiResponse(responseCode = "409", description = "요청 충돌이 발생했습니다.",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(example = "{\"code\": 409, \"message\": \"요청 충돌이 발생했습니다.\", \"data\": null}")))
})
public @interface ErrorApiResponses {
}
