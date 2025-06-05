package com.springliviu.notice.controller;

import com.springliviu.notice.dto.NoteRequest;
import com.springliviu.notice.dto.NoteResponse;
import com.springliviu.notice.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @RestController exposes methods as REST endpoints
@RequestMapping("/api/notes") // base URL for all note endpoints
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return noteService.getAllNotes(); // returns all notes as DTOs
    }

    @PostMapping
    public NoteResponse createNote(@Valid @RequestBody NoteRequest request) {
        return noteService.createNote(request); // creates new note from request DTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id) // fetches by ID
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        return noteService.updateNote(id, request) // updates note
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteService.deleteNote(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
