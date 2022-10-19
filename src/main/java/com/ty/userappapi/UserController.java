package com.ty.userappapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.userappapi.User;
import com.ty.userappapi.UserRepository;

public class UserController {
	@Autowired
	UserRepository userRepository;
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
		User u=userRepository.save(user);
		return user;
	}
	
	@GetMapping("/getuser")
	public User getUser(@RequestParam int id) {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			User c =opt.get();
			return c;
		} 
		else {
			return null;
		 }
		
	  }
	@GetMapping("/getall")
	public List<User> getAllUser() {
		return userRepository.findAll();
		
	}
	@DeleteMapping("/deleteuser")
	public String deleteUser(@RequestParam int id) {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			User c =opt.get();
			userRepository.delete(c);
		return "User is deleted";
		} else {
		return "Id not found";
		}
	}
	@PutMapping("/updateuser")
	public User updateUser(@RequestParam int id,@RequestBody User user) {
		Optional<User> opt = userRepository.findById(id);
		if(opt.isPresent()) {
			User ret=opt.get();
			ret.setName(user.getName());
			ret.setEmail(user.getEmail());
			ret.setPhone(user.getPhone());
			ret.setPassword(user.getPassword());
			userRepository.save(ret);
			return ret;
		} else {
			return null;
		}
	}

}
