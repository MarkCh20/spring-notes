package com.example.notesapp;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteServiceImpl implements NoteService {

    private final Map<Long, Note> notes = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public NoteServiceImpl() {
        add(new Note(null, "Example Note-1", "This is a sample note 1."));
        add(new Note(null, "Example Note-2", "This is a sample note 2."));
        add(new Note(null, "Example Note-3", "This is a sample note 3."));
        add(new Note(null, "Example Note-4", "This is a sample note 4."));
        add(new Note(null, "Example Note-5", "This is a sample note 5."));
        add(new Note(null, "Example Note-6", "This is a sample note 6."));
        add(new Note(null, "Example Note-7", "This is a sample note 7."));
        add(new Note(null, "Example Note-8", "This is a sample note 8."));
        add(new Note(null, "Example Note-9", "This is a sample note 9."));
        add(new Note(null, "Example Note-10", "This is a sample note 10."));
    }


    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        long id = idGenerator.getAndIncrement();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with id " + id + " not found.");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException("Note with id " + note.getId() + " not found.");
        }
        Note existing = notes.get(note.getId());
        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());
    }

    public Note getById(long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("Note with id " + id + " not found.");
        }
        return note;
    }
}
