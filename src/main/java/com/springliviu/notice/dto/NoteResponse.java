package com.springliviu.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Swagger annotation for model schema
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter // Lombok: generates getters
@Setter // Lombok: generates setters
@AllArgsConstructor // Lombok: generates constructor with all fields
@Schema(description = "Response object representing a saved note")
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id; // ID of the note

    @Schema(description = "Title of the note", example = "Meeting Notes")
    private String title; // Title of the note

    @Schema(description = "Content of the note", example = "Discuss project deadlines and budget.")
    private String content; // Body text of the note

    @Schema(description = "Timestamp when the note was created", example = "2025-06-06T12:00:00")
    private LocalDateTime createdAt; // Note creation timestamp

    @Schema(description = "Timestamp when the note was last updated", example = "2025-06-06T12:30:00")
    private LocalDateTime updatedAt; // Last update timestamp
}
