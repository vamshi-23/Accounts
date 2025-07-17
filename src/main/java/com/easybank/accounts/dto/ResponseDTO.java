package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "This schema holds the response details for API requests in Easy Bank",
        requiredProperties = {"statusCode", "message"})
public class ResponseDTO {

    @Schema(
            description = "HTTP status code of the response")
    private String statusCode;

    @Schema(
            description = "Message providing additional information about the response")
    private String message;
}
