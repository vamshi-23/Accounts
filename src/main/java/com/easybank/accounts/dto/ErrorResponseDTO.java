package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "This schema holds the error response details for API requests in Easy Bank",
        requiredProperties = {"apiPath", "errorCode", "errorMessage", "errorTime"})
public class ErrorResponseDTO {

    @Schema(
            description = "API path where the error occurred",
            example = "/api/fetch")
    private String apiPath;

    @Schema(
            description = "HTTP status code of the error",
            example = "404")
    private HttpStatus errorCode;

    @Schema(
            description = "Message providing additional information about the error",
            example = "Account not found for the provided mobile number")
    private String errorMessage;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2023-10-01T12:00:00")
    private LocalDateTime errorTime;
}
