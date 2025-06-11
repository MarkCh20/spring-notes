package com.example.notesapp.integration;

import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NoteIntegrationTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    void whenSaveNote_thenFoundById() {
        Note note = new Note(null, "Integration", "Test");
        note = noteRepository.save(note);

        Note found = noteRepository.findById(note.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Integration", found.getTitle());
    }

    @Test
    void whenFindAll_thenReturnNotes() {
        noteRepository.save(new Note(null, "A", "B"));
        List<Note> all = noteRepository.findAll();

        assertFalse(all.isEmpty());
    }

    @Test
    void whenDelete_thenNoteIsRemoved() {
        Note note = noteRepository.save(new Note(null, "To Delete", "X"));
        noteRepository.deleteById(note.getId());

        assertFalse(noteRepository.findById(note.getId()).isPresent());
    }
}
