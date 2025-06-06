package com.springliviu.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema; // Swagger annotation for model schema
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok: generates getters
@Setter // Lombok: generates setters
@Schema(description = "Request body for creating or updating a note")
public class NoteRequest {

    @NotBlank(message = "Title must not be blank") // Validates that title is not blank
    @Schema(description = "Title of the note", example = "Meeting Notes")
    private String title; // Required title field

    @Schema(description = "Content of the note", example = "Discuss project deadlines and budget.")
    private String content; // Optional content field
}
