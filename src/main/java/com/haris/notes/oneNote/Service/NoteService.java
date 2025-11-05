package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.ContentObject;
import com.haris.notes.oneNote.Model.Notes;
import com.haris.notes.oneNote.Model.User;
import com.haris.notes.oneNote.Repository.NoteRepository;
import com.haris.notes.oneNote.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements IntService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepo userRepo;


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

            User user = userRepo.findByUsername(ownerName);


        Notes newnote =  new Notes();
        newnote.setContent(contentObject.getContent());
        newnote.setOwnerName(ownerName);
        newnote.setUser(user);
        noteRepository.save(newnote);


            return ResponseEntity.ok(newnote);
     }

    @Override
    public ResponseEntity<?> dltNote(int noteid ,String username) {


        if (noteRepository.existsById(noteid)) {

            for (Notes notes : noteRepository.findAll()) {
                if (notes.getNoteid() == noteid && notes.getOwnerName().equals(username) ) {
                    noteRepository.delete(notes);
                    return ResponseEntity.ok("Deleted THe Note with id " + noteid);
                }
            }

        }



        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");

    }

    @Override
    public ResponseEntity<?> udpateNote(Notes note , String username) {
                for (Notes note1 : noteRepository.findAll()){
                    if(note1.getNoteid()==note.getNoteid() && note1.getOwnerName().equals(username)){
                           noteRepository.UpdateNote(note.getNoteid(),note.getOwnerName(),note.getContent());
                          return ResponseEntity.ok("Update Succesfully");
                    }
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Updated");





    }

}
