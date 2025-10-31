package com.haris.notes.oneNote.Repository;

import com.haris.notes.oneNote.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Integer> {

    public User findByUsername(String Username);
}
