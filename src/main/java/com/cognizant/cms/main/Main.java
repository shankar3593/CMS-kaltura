//package com.cognizant.cms.main;
//
//
//import java.util.LinkedList;
//import java.util.List;
//
//import com.cognizant.cms.model.*;
//import com.cognizant.cms.model.Module;
//
//public class Main {
//
//	public static void main(String[] args) {
//		
//		
//		Permission permission1=new Permission();
//		permission1.setCreate(true);
//		Module module1=new Module("contentManagement", permission1);
//		Module module2=new Module("home", permission1);
//		List<Module> modules=new LinkedList<Module>();
//		modules.add(module1);
//		modules.add(module2);
//		
//		Role role=new Role("admin","ADMINISTRATOR", modules);
//		User user=new User("neha123", "1234neha", role, "Neha", "Dilkash");
//		
//		System.out.println(user);
//	}
//
//}
