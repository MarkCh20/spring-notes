package com.example.notesapp.controller;

import com.example.notesapp.model.Note;
import com.example.notesapp.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("note", new Note());
        return "note-add";
    }

    @PostMapping("/add")
    public String addNote(@Valid @ModelAttribute("note") Note note,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "note-add";
        }
        noteService.add(note);
        return "redirect:/note/list";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note-edit";
    }

    @PostMapping("/edit")
    public String saveEdit(@Valid @ModelAttribute("note") Note note,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors — return to edit form
            model.addAttribute("note", note);
            return "note-edit";
        }
        noteService.update(note);
        return "redirect:/note/list";
    }
}
