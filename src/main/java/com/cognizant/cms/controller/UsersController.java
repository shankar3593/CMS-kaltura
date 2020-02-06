package com.cognizant.cms.controller;

//import java.util.Base64;
//import java.util.Base64.Decoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.exception.UsernameNotFoundException;
import com.cognizant.cms.model.Users;
import com.cognizant.cms.repository.UsersRepository;



@RestController
@RequestMapping("/login")
public class UsersController {
	
	@Autowired
	UsersRepository u_repo;
	
	
	
	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public String getAll( Users users) {
				
		List<Users> all=u_repo.findAll();
			//System.out.println(all);
			if(all!=null) {
				//if(((Users) all).getUsername().equals(users.getUsername())&&((Users) all).getPassword().equals(users.getPassword())) {
					//return "True";
				return "true";
				}else {
			//}
		
					throw new UsernameNotFoundException();
				}
		
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Users getAllUSERS(@RequestBody Users users) {
//		Decoder decoder = Base64.getDecoder();
//		byte[] decodedByte = decoder.decode(password);
//		String decodedString = new String(decodedByte);
//		System.out.println(decodedString);
		//System.out.println(users.getPassword().equals(decodedString));
		Users all=u_repo.findByUsername(users.getUsername());
			if(all!=null) {
				if(all.getUsername().equals(users.getUsername())&&all.getPassword().equals(users.getPassword())) {
					return all;
				}
			}
		
			// TODO: handle exception
			throw new UsernameNotFoundException();
		
	}
	
	
//	Decoder decoder = Base64.getDecoder();
//	byte[] decodedByte = decoder.decode(encodedString);
//	String decodedString = new String(decodedByte);
//	System.out.println(decodedString);  // Outputs: "Highlight"
	
	
	
}
