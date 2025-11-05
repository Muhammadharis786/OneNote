package com.haris.notes.oneNote.Repository;

import com.haris.notes.oneNote.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Integer> {

  User findByUsername(String Username);




    Optional<User> findByUsernameAndPassword(String username, String password);
}
