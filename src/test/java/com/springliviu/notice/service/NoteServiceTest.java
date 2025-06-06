package com.springliviu.notice.service;

import com.springliviu.notice.dto.NoteRequest;
import com.springliviu.notice.dto.NoteResponse;
import com.springliviu.notice.model.Note;
import com.springliviu.notice.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for NoteService
 */
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    /**
     * Tests the createNote method to ensure a note is saved and mapped correctly.
     */
    @Test
    void createNote_shouldSaveNoteAndReturnNoteResponse() {
        // Arrange
        NoteRequest request = new NoteRequest();
        request.setTitle("Test Title");
        request.setContent("Test Content");

        Note savedNote = Note.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .createdAt(LocalDateTime.of(2025, 6, 6, 12, 0))
                .updatedAt(LocalDateTime.of(2025, 6, 6, 12, 0))
                .build();

        when(noteRepository.save(any(Note.class))).thenReturn(savedNote);

        // Act
        NoteResponse response = noteService.createNote(request);

        // Assert
        assertNotNull(response);
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Content", response.getContent());
        assertEquals(1L, response.getId());
        assertNotNull(response.getCreatedAt());
        assertNotNull(response.getUpdatedAt());

        verify(noteRepository, times(1)).save(any(Note.class));
    }

    /**
     * Tests updateNote when the note exists and should be updated successfully.
     */
    @Test
    void updateNote_shouldUpdateAndReturnNoteResponse_whenNoteExists() {
        // Arrange
        Long noteId = 1L;
        NoteRequest request = new NoteRequest();
        request.setTitle("Updated Title");
        request.setContent("Updated Content");

        Note existingNote = Note.builder()
                .id(noteId)
                .title("Old Title")
                .content("Old Content")
                .createdAt(LocalDateTime.of(2025, 6, 6, 12, 0))
                .updatedAt(LocalDateTime.of(2025, 6, 6, 12, 0))
                .build();

        Note updatedNote = Note.builder()
                .id(noteId)
                .title("Updated Title")
                .content("Updated Content")
                .createdAt(existingNote.getCreatedAt())
                .updatedAt(LocalDateTime.of(2025, 6, 6, 13, 0))
                .build();

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(existingNote));
        when(noteRepository.save(any(Note.class))).thenReturn(updatedNote);

        // Act
        Optional<NoteResponse> result = noteService.updateNote(noteId, request);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Updated Title", result.get().getTitle());
        assertEquals("Updated Content", result.get().getContent());
        verify(noteRepository).save(existingNote);
    }

    /**
     * Tests deleteNote when the note exists.
     */
    @Test
    void deleteNote_shouldReturnTrue_whenNoteExists() {
        // Arrange
        Long noteId = 1L;
        when(noteRepository.existsById(noteId)).thenReturn(true);

        // Act
        boolean deleted = noteService.deleteNote(noteId);

        // Assert
        assertTrue(deleted);
        verify(noteRepository).deleteById(noteId);
    }

    /**
     * Tests deleteNote when the note does not exist.
     */
    @Test
    void deleteNote_shouldReturnFalse_whenNoteDoesNotExist() {
        // Arrange
        Long noteId = 999L;
        when(noteRepository.existsById(noteId)).thenReturn(false);

        // Act
        boolean deleted = noteService.deleteNote(noteId);

        // Assert
        assertFalse(deleted);
        verify(noteRepository, never()).deleteById(anyLong());
    }

}
