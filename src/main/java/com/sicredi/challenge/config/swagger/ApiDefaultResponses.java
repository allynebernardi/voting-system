package com.sicredi.challenge.config.swagger;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(
        value = {
                @ApiResponse(code = 200, message = "successful operation"),
                @ApiResponse(code = 400, message = "Invalid Parameters"),
                @ApiResponse(code = 404, message = "Agenda Not Found"),
                @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
        }
 )
public @interface ApiDefaultResponses {
}
