package com.redisWithSpringBootApplicationForCache.Service;


import com.redisWithSpringBootApplicationForCache.Repository.EnoteRepository;
import com.redisWithSpringBootApplicationForCache.entities.Enote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnoteServiceImpl implements EnoteService {

    @Autowired
    private final EnoteRepository enoteRepository;

    public EnoteServiceImpl(EnoteRepository enoteRepository) {
        this.enoteRepository = enoteRepository;
    }

    @Override
    @CachePut(value="enote",key = "#enote.id")
    public Enote createNote(Enote enote) {
        return enoteRepository.save(enote);
    }

    @Override
    @Cacheable(value = "enote",key = "#id")
    public Enote getNoteById(String id) {
        return enoteRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Note not found with ID: " + id));
    }

    @Override
    public Enote updateNote(String id, Enote updatedEnote) {
        Enote existing = getNoteById(id);
        existing.setName(updatedEnote.getName());
        existing.setContent(updatedEnote.getContent());
        existing.setLive(updatedEnote.isLive());
        return enoteRepository.save(existing);
    }

    @Override
    @CacheEvict(value = "enote",key = "#id")
    public void deleteNote(String id) {
        if (!enoteRepository.existsById(Integer.valueOf(id))) {
            throw new RuntimeException("Note not found with ID: " + id);
        }
        enoteRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public List<Enote> getAllNotes() {
        return enoteRepository.findAll();
    }
}