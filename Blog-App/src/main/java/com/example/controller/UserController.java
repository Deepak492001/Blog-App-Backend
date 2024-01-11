package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@CrossOrigin("http://localhost:5173")
//@CrossOrigin("*")
public class UserController {
 @Autowired
 private UserService userService;
 
 @PostMapping("/add-user")
 public void saveUser(@RequestBody User newUser) {
	 System.out.println(newUser);
	  this.userService.saveUser(newUser);
 }
 
 @PostMapping("/update-password/{userEmail}")
 public String updateUser(@PathVariable("userEmail")String userEmail,@RequestBody String password) {
	 User user=this.userService.getUserByEmail(userEmail);	
	 if (user != null) {
	         user.setPassword(password);
	         this.userService.saveUser(user); // Save the updated user entity
	         return "Password updated successfully";
	     } else {
	         return "User not found";
	     }

 }


// @PutMapping("/update-password/{userEmail}")
// public String updateUser(@PathVariable("userEmail")String userEmail,@RequestBody String password) {
//	 User user=this.userService.getUserByEmail(userEmail);	
//	 if (user != null) {
//	         user.setPassword(password);
//	         this.userService.saveUser(user); // Save the updated user entity
//	         return "Password updated successfully";
//	     } else {
//	         return "User not found";
//	     }
//
// }

 
}
