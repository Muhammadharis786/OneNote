package com.haris.notes.oneNote.Repository;

import com.haris.notes.oneNote.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepository  extends JpaRepository<Notes , Integer> {



    public List<Notes>  findByOwnerName(String ownerName);
    public  boolean existsByOwnerName (String ownerName);


       @Modifying
       @Transactional
       @Query("update Notes  n set n.content =:notecontent , n.ownerName=:ownername where n.noteid=:noteid")
    void UpdateNote(   @Param("noteid")  int noteid,
                       @Param("ownername") String name,
                       @Param("notecontent") String notecontent);
}
