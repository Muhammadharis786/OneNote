package com.haris.notes.oneNote.RestController;


import com.haris.notes.oneNote.Model.Notes;
import com.haris.notes.oneNote.Service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NoteController {


    @Autowired
    NoteService noteServices;

    @GetMapping("api/getNotes")
    public List<Notes> GetNotes() {
        return noteServices.getAllNotes();

    }

    @GetMapping("api/getNotesByOwner/{OwnerName}")
    public    ResponseEntity<?>  getnotebyowner (   @PathVariable  String OwnerName){

        return    noteServices.getbyownerName(OwnerName);
    }

    @PostMapping("api/addNote")
    public Notes addnote ( @RequestBody Notes notes){

        return noteServices.addNote(notes);
    }


}
