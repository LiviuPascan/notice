package com.springliviu.notice.service;

import com.springliviu.notice.dto.NoteRequest;
import com.springliviu.notice.dto.NoteResponse;
import com.springliviu.notice.model.Note;
import com.springliviu.notice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // @Service marks this class as a Spring service component
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteResponse> getAllNotes() {
        return noteRepository.findAll().stream() // gets all notes
                .map(this::mapToResponse) // maps to response DTOs
                .collect(Collectors.toList());
    }

    public Optional<NoteResponse> getNoteById(Long id) {
        return noteRepository.findById(id) // finds note by ID
                .map(this::mapToResponse); // maps to response DTO
    }

    public NoteResponse createNote(NoteRequest request) {
        Note note = Note.builder() // builds new note from request
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Note saved = noteRepository.save(note); // saves note
        return mapToResponse(saved); // returns DTO
    }

    public Optional<NoteResponse> updateNote(Long id, NoteRequest request) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(request.getTitle()); // updates title
                    note.setContent(request.getContent()); // updates content
                    Note updated = noteRepository.save(note); // saves updated note
                    return mapToResponse(updated); // returns DTO
                });
    }

    public boolean deleteNote(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id); // deletes note
            return true; // deletion success
        }
        return false; // note not found
    }

    private NoteResponse mapToResponse(Note note) {
        return new NoteResponse( // converts entity to DTO
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
