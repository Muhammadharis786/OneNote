package com.haris.notes.oneNote.RestController;

import com.haris.notes.oneNote.Model.User;
import com.haris.notes.oneNote.Repository.UserRepo;
import com.haris.notes.oneNote.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service;



@PostMapping("api/user/Register")
    public ResponseEntity<?> registerUser (@RequestBody User user){
    return service.AddRegister(user);
}

@DeleteMapping("api/user/delete/{uid}")

    public  ResponseEntity<?> dltUser (@PathVariable int uid){
    return  service.deleteUsers (uid);

}

@PostMapping ("api/user/getuser")
    public ResponseEntity<?> getUser ( @RequestBody User user){
             return service.findUser(user) ;

}








}
