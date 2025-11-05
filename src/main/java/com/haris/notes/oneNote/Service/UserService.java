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

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepo userRepo;



    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user =  userRepo.findByUsername(username);
        if(user==null){

            throw new UsernameNotFoundException("User Not Found");
        }

        return new MyPrincipal(user);

   }

   public ResponseEntity<?> AddRegister (User user){

       List<User> listOfUser =  userRepo.findAll();
       for (User user1 : listOfUser){
           if(user1.getUsername().equals(user.getUsername())){
               System.out.println("user Already Taken");
               return ResponseEntity.status(HttpStatus.IM_USED).body("User Name is Already Used");
           }
       }

      user.setPassword(encoder.encode(user.getPassword()));
        return   ResponseEntity.ok(userRepo.save(user))  ;
   }


   //this is for delteUser

    public ResponseEntity<?> deleteUsers(int uid) {
        if(userRepo.existsById(uid)){
        User user =  userRepo.findById(uid).get();

        ;
            userRepo.delete(user);

          return  ResponseEntity.ok(  "Deleted Successfully" ) ;
        }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exist");

    }

    public ResponseEntity<?> findUser(User user) {

                   Optional<User>    IsUser = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));

                   if(IsUser.isPresent()){
                       User FoundUser = IsUser.get();

                       if(encoder.matches(user.getPassword(),FoundUser.getPassword())){
                           System.out.println("find users");

                           return ResponseEntity.ok("User Found");
                       }

                   }

                       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Found");




    }
}
