package com.haris.notes.oneNote.Service;

import com.haris.notes.oneNote.Model.MyPrincipal;
import com.haris.notes.oneNote.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.haris.notes.oneNote.Model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user =  userRepo.findByUsername(username);
        if(user==null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new MyPrincipal(user);

   }

   public ResponseEntity<?> getRegister (User user){

       List<User> listOfUser =  userRepo.findAll();
       for (User user1 : listOfUser){
           if(user1.getUsername().equals(user.getUsername())){
               return ResponseEntity.status(HttpStatus.IM_USED).body("User Name is Already Used");
           }
       }

      user.setPassword(encoder.encode(user.getPassword()));
        return   ResponseEntity.ok(userRepo.save(user))  ;
   }

}
