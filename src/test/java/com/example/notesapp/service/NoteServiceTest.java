package com.example.notesapp.service;

import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNote_whenAdd_thenSaved() {
        Note note = new Note(null, "Title", "Content");
        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.add(note);

        assertEquals("Title", result.getTitle());
        verify(noteRepository).save(note);
    }

    @Test
    void whenListAll_thenReturnsAllNotes() {
        List<Note> mockNotes = List.of(new Note(1L, "A", "B"));
        when(noteRepository.findAll()).thenReturn(mockNotes);

        List<Note> result = noteService.listAll();

        assertEquals(1, result.size());
    }

    @Test
    void whenDelete_thenRepositoryCalled() {
        noteService.deleteById(1L);
        verify(noteRepository).deleteById(1L);
    }

    @Test
    void whenGetById_ifExists_thenReturnNote() {
        Note note = new Note(1L, "A", "B");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note found = noteService.getById(1L);

        assertEquals("A", found.getTitle());
    }

    @Test
    void whenGetById_ifNotFound_thenThrow() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> noteService.getById(1L));
    }

    @Test
    void whenUpdate_ifExists_thenSave() {
        Note note = new Note(1L, "Updated", "Text");
        when(noteRepository.existsById(1L)).thenReturn(true);

        noteService.update(note);

        verify(noteRepository).save(note);
    }

    @Test
    void whenUpdate_ifNotExists_thenThrow() {
        Note note = new Note(1L, "X", "Y");
        when(noteRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> noteService.update(note));
    }
}
