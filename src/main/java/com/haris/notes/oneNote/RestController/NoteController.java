package com.haris.notes.oneNote.RestController;


import com.haris.notes.oneNote.Model.ContentObject;
import com.haris.notes.oneNote.Model.Notes;
import com.haris.notes.oneNote.Service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController

public class NoteController {


    @Autowired
    NoteService noteServices;

    @GetMapping("api/getNotes")
    public ResponseEntity<?> GetNotes( @AuthenticationPrincipal UserDetails userDetails) {

        String Username = userDetails.getUsername();
        return noteServices.getAllNotes(Username);

    }


    @GetMapping("api/getNotesByOwner")
    public    ResponseEntity<?>  getnotebyowner (
            @AuthenticationPrincipal UserDetails userDetails
                                                ){
    String ownerName  =  userDetails.getUsername();
        return    noteServices.getbyownerName(ownerName);
    }

    @PostMapping("api/addNote")
    public ResponseEntity<?> addnote (@RequestBody ContentObject contentObject,
                                      @AuthenticationPrincipal UserDetails userDetails){
        String ownerName  =  userDetails.getUsername();
        return noteServices.addNote(contentObject ,ownerName);
    }

    @DeleteMapping("api/DeleteNote/{noteid}")
    public ResponseEntity<?> dltNote (@PathVariable int noteid,
                                      @AuthenticationPrincipal UserDetails userDetails
    ){
        return noteServices.dltNote(noteid);
    }

    @PutMapping("api/UpdateNote")

    public ResponseEntity<?> updateNote ( @RequestBody   Notes note,
                                          @AuthenticationPrincipal UserDetails userDetails ){

        return noteServices.udpateNote(note);
    }


}
