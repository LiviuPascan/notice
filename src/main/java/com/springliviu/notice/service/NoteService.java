package com.springliviu.notice.service;

import com.springliviu.notice.model.Note;
import com.springliviu.notice.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // @Service marks this class as a service component in Spring
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll(); // returns all notes
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id); // returns note by ID if found
    }

    public Note createNote(Note note) {
        return noteRepository.save(note); // saves a new note
    }

    public Optional<Note> updateNote(Long id, Note updatedNote) {
        return noteRepository.findById(id) // finds existing note
                .map(note -> {
                    note.setTitle(updatedNote.getTitle()); // updates title
                    note.setContent(updatedNote.getContent()); // updates content
                    return noteRepository.save(note); // saves updated note
                });
    }

    public boolean deleteNote(Long id) {
        if (noteRepository.existsById(id)) { // checks if note exists
            noteRepository.deleteById(id); // deletes the note
            return true; // return true if deleted
        }
        return false; // return false if not found
    }
}
