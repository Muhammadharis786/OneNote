package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.ContentObject;
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
    public ResponseEntity<?> getAllNotes(String Username) {
            if(Username.equals("admin")){
                return    ResponseEntity.ok(noteRepository.findAll())  ;

            }

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You Can't Access");


    }

    @Override
    public ResponseEntity<?> getbyownerName(String OwnerName) {


            List<Notes> notes = noteRepository.findByOwnerName(OwnerName);
            return ResponseEntity.ok(notes);

    }

    @Override
    public ResponseEntity<?> addNote(ContentObject contentObject, String ownerName) {


        Notes newnote =  new Notes();
        newnote.setContent(contentObject.getContent());
        newnote.setOwnerName(ownerName);
        noteRepository.save(newnote);


            return ResponseEntity.ok(newnote);
     }

    @Override
    public ResponseEntity<?> dltNote(int noteid) {


        if (noteRepository.existsById(noteid)) {

            for (Notes notes : noteRepository.findAll()) {
                if (notes.getNoteid() == noteid ) {
                    noteRepository.delete(notes);
                    return ResponseEntity.ok("Deleted THe Note with id " + noteid);
                }
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");

    }

    @Override
    public ResponseEntity<?> udpateNote(Notes note) {

        for (Notes notes : noteRepository.findAll()) {
            if (notes.getNoteid() != note.getNoteid()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note Not Exits");
            }
        }
        String name = note.getOwnerName();
        int noteid = note.getNoteid();
        String notecontent = note.getContent();

         noteRepository.UpdateNote(noteid, name, notecontent);
         return ResponseEntity.ok(note);


    }

}
