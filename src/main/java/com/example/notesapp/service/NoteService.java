package com.example.notesapp.service;

import com.example.notesapp.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> listAll();

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);

    Note getById(long id);
}
