package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.Notes;
import com.haris.notes.oneNote.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements IntService {

    @Autowired
    NoteRepository noteRepository;


    @Override
    public List<Notes> getAllNotes() {
        List<Notes> list = noteRepository.findAll();
        return list;
    }

    @Override
    public ResponseEntity<?> getbyownerName(String OwnerName) {
        if(noteRepository.existsByOwnerName(OwnerName)){

        List<Notes> notes = noteRepository.findByOwnerName(OwnerName);
        return ResponseEntity.ok(notes);
        }
        else
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found");
    }

    @Override
    public Notes addNote(Notes note) {
        Notes notes = noteRepository.save(note);

        return notes;
    }

}
