package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.Notes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IntService  {

    public List<Notes> getAllNotes();
    public   ResponseEntity<?>  getbyownerName (String OwnerName);
    public Notes addNote (Notes note);

}
