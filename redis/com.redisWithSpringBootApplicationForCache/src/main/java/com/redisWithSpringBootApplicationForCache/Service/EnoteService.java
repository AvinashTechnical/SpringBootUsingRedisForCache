package com.redisWithSpringBootApplicationForCache.Service;



import com.redisWithSpringBootApplicationForCache.entities.Enote;

import java.util.List;

public interface EnoteService {
    Enote createNote(Enote enote);
    Enote getNoteById(String id);
    Enote updateNote(String id, Enote enote);
    void deleteNote(String id);
    List<Enote> getAllNotes();
}