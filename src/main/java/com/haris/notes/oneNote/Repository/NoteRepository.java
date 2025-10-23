package com.haris.notes.oneNote.Repository;

import com.haris.notes.oneNote.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository  extends JpaRepository<Notes , Integer> {



    public List<Notes>  findByOwnerName(String ownerName);
    public  boolean existsByOwnerName (String ownerName);
}
