package com.thomasvitale.application.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteDTO> getAllNotes() {
        List<NoteDTO> noteDTOS = new ArrayList<>();
        noteRepository.findAll().forEach(organization ->
                noteDTOS.add(new NoteDTO(organization.getTitle(), organization.getContent()))
        );
        return noteDTOS;
    }
}
