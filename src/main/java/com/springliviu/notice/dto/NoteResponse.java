package com.springliviu.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class NoteResponse {

    private Long id; // id of the note
    private String title; // title of the note
    private String content; // content of the note
    private LocalDateTime createdAt; // creation timestamp
    private LocalDateTime updatedAt; // last update timestamp
}
