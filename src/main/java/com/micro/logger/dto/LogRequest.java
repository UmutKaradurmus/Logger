package com.micro.logger.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LogRequest {
    @Schema(description = "Log mesajı", example = "Something happened...")
    private String message;
}