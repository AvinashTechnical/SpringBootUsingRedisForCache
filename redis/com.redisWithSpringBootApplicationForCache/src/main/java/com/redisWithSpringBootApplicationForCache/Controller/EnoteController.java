package com.redisWithSpringBootApplicationForCache.Controller;


import com.redisWithSpringBootApplicationForCache.Service.EnoteService;
import com.redisWithSpringBootApplicationForCache.entities.Enote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class EnoteController {

    @Autowired
    private final EnoteService enoteService;

    public EnoteController(EnoteService enoteService) {
        this.enoteService = enoteService;
    }

    // Create note
    @PostMapping
    public ResponseEntity<Enote> createNote(@RequestBody Enote enote) {
        return ResponseEntity.ok(enoteService.createNote(enote));
    }

    // Get note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enote> getNoteById(@PathVariable String id) {
        return ResponseEntity.ok(enoteService.getNoteById(id));
    }

    // Update note
    @PutMapping("/{id}")
    public ResponseEntity<Enote> updateNote(@PathVariable String id, @RequestBody Enote enote) {
        return ResponseEntity.ok(enoteService.updateNote(id, enote));
    }

    // Delete note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        enoteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // Get all notes
    @GetMapping
    public ResponseEntity<List<Enote>> getAllNotes() {
        return ResponseEntity.ok(enoteService.getAllNotes());
    }
}
