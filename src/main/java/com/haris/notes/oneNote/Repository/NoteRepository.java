package com.haris.notes.oneNote.Repository;

import com.haris.notes.oneNote.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository  extends JpaRepository<Notes , Integer> {
}
