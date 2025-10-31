package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.ContentObject;
import com.haris.notes.oneNote.Model.Notes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IntService {

    public ResponseEntity<?> getAllNotes(String Username);

    public ResponseEntity<?> getbyownerName(String OwnerName);

    public ResponseEntity<?> addNote(ContentObject contentObject , String ownerName);

    ResponseEntity<?> dltNote(int noteid);


    ResponseEntity<?> udpateNote(Notes note);
}
