package com.springliviu.notice.service;

import com.springliviu.notice.dto.NoteRequest; // Imports request DTO
import com.springliviu.notice.dto.NoteResponse; // Imports response DTO
import com.springliviu.notice.model.Note; // Imports Note entity
import com.springliviu.notice.repository.NoteRepository; // Imports repository interface
import org.springframework.stereotype.Service; // Marks this class as a Spring service component

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Registers this class as a service in the Spring context
public class NoteService {

    private final NoteRepository noteRepository; // Repository dependency for DB operations

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteResponse> getAllNotes() {
        return noteRepository.findAll().stream() // Retrieves all notes from the database
                .map(this::mapToResponse) // Maps each Note to a NoteResponse DTO
                .collect(Collectors.toList()); // Collects into a list
    }

    public Optional<NoteResponse> getNoteById(Long id) {
        return noteRepository.findById(id) // Searches for note by ID
                .map(this::mapToResponse); // If found, maps to NoteResponse
    }

    public NoteResponse createNote(NoteRequest request) {
        Note note = Note.builder() // Builds a new Note entity using builder pattern
                .title(request.getTitle()) // Sets title from request
                .content(request.getContent()) // Sets content from request
                .build();

        Note saved = noteRepository.save(note); // Saves the new note to the database
        return mapToResponse(saved); // Converts and returns as response DTO
    }

    public Optional<NoteResponse> updateNote(Long id, NoteRequest request) {
        return noteRepository.findById(id) // Finds note by ID
                .map(note -> {
                    note.setTitle(request.getTitle()); // Updates title field
                    note.setContent(request.getContent()); // Updates content field
                    Note updated = noteRepository.save(note); // Saves changes to database
                    return mapToResponse(updated); // Converts and returns as DTO
                });
    }

    public boolean deleteNote(Long id) {
        if (noteRepository.existsById(id)) { // Checks if note exists
            noteRepository.deleteById(id); // Deletes the note from the database
            return true; // Returns success
        }
        return false; // Returns failure if note was not found
    }

    private NoteResponse mapToResponse(Note note) {
        return new NoteResponse( // Builds and returns a response DTO from entity
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
