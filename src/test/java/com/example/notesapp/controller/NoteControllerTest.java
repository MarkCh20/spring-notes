package com.example.notesapp.controller;

import com.example.notesapp.model.Note;
import com.example.notesapp.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService;

    @Test
    @WithMockUser(username = "testuser")
    void whenListNotes_thenReturnListView() throws Exception {
        when(noteService.listAll()).thenReturn(List.of(new Note(1L, "A", "B")));

        mockMvc.perform(get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("note-list"))
                .andExpect(model().attributeExists("notes"));

        verify(noteService).listAll();
    }

    @TestConfiguration
    static class MockNoteServiceConfig {
        @Bean
        @Primary
        public NoteService mockNoteService() {
            return mock(NoteService.class);
        }
    }
}
