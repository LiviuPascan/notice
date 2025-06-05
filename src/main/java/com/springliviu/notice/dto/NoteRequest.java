package com.springliviu.notice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequest {

    @NotBlank(message = "Title must not be blank") // @NotBlank enforces that title is required
    private String title;

    private String content; // content is optional
}

