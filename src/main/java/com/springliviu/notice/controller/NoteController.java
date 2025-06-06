package com.springliviu.notice.controller;

import com.springliviu.notice.dto.NoteRequest;
import com.springliviu.notice.dto.NoteResponse;
import com.springliviu.notice.service.NoteService;
import io.swagger.v3.oas.annotations.Operation; // Swagger annotation for endpoint descriptions
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @RestController exposes methods as REST endpoints
@RequestMapping("/api/notes") // Base URL for all note endpoints
public class NoteController {

    private final NoteService noteService; // Service layer dependency

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "Get all notes", description = "Returns a list of all saved notes")
    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return noteService.getAllNotes(); // Retrieves all notes as response DTOs
    }

    @Operation(summary = "Create a new note", description = "Creates a new note from the given request body")
    @PostMapping
    public NoteResponse createNote(@Valid @RequestBody NoteRequest request) {
        return noteService.createNote(request); // Creates and returns the saved note
    }

    @Operation(summary = "Get a note by ID", description = "Fetches a note by its unique identifier")
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id) // Tries to find the note
                .map(ResponseEntity::ok) // Returns 200 OK with body
                .orElse(ResponseEntity.notFound().build()); // Returns 404 if not found
    }

    @Operation(summary = "Update a note", description = "Updates an existing note with new title and content")
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        return noteService.updateNote(id, request) // Updates note if it exists
                .map(ResponseEntity::ok) // Returns updated note
                .orElse(ResponseEntity.notFound().build()); // Returns 404 if note not found
    }

    @Operation(summary = "Delete a note", description = "Deletes a note by its ID if it exists")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteService.deleteNote(id)) {
            return ResponseEntity.noContent().build(); // Returns 204 if deletion successful
        }
        return ResponseEntity.notFound().build(); // Returns 404 if note not found
    }
}
