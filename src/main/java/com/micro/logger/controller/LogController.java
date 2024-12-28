package com.micro.logger.controller;

import com.micro.logger.dto.LogRequest;
import com.micro.logger.model.LogEntity;
import com.micro.logger.repository.LogRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Log API", description = "Log CRUD işlemleri")
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Operation(summary = "Yeni bir log kaydı oluştur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log kaydı başarıyla oluşturuldu"),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek gövdesi"),
            @ApiResponse(responseCode = "500", description = "Sunucu hatası")
    })
    @PostMapping
    public ResponseEntity<LogEntity> createLog(@RequestBody LogRequest logRequest) {
        LogEntity logEntity = LogEntity.builder()
                .id(UUID.randomUUID().toString())
                .message(logRequest.getMessage())
                .createdAt(Instant.now())
                .build();

        LogEntity savedLog = logRepository.save(logEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
    }

    @Operation(summary = "Belirli bir log kaydını getir")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log kaydı bulundu"),
            @ApiResponse(responseCode = "404", description = "Log kaydı bulunamadı"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<LogEntity> getLog(@PathVariable String id) {
        return logRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
