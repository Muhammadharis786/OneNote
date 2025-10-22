package com.haris.notes.oneNote.RestController;


import com.haris.notes.oneNote.Model.Notes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class NoteController {

    @GetMapping("api/getNotes")
    public List<Notes> GetNotes() {
        List<Notes> list = new ArrayList<>();
        return list;
    }


}
