package com.springliviu.notice.controller;

import com.springliviu.notice.model.Note;
import com.springliviu.notice.service.NoteService;
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
    public List<Note> getAllNotes() {
        return noteService.getAllNotes(); // gets all notes via service
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note); // creates a note via service
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id) // gets note by ID
                .map(ResponseEntity::ok) // returns 200 OK
                .orElse(ResponseEntity.notFound().build()); // or 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return noteService.updateNote(id, updatedNote) // updates note by ID
                .map(ResponseEntity::ok) // returns updated note
                .orElse(ResponseEntity.notFound().build()); // or 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteService.deleteNote(id)) { // deletes note via service
            return ResponseEntity.noContent().build(); // returns 204 No Content
        }
        return ResponseEntity.notFound().build(); // or 404 Not Found
    }
}
