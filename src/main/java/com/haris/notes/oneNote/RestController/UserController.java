package com.haris.notes.oneNote.RestController;

import com.haris.notes.oneNote.Model.User;
import com.haris.notes.oneNote.Repository.UserRepo;
import com.haris.notes.oneNote.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    UserService service;
      public   UserController (UserService service){
                    this.service=service;
}


@PostMapping("api/Register")
    public ResponseEntity<?> registerUser (@RequestBody User user){
    return service.getRegister(user);


}




}
