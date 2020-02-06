package com.cognizant.cms.controller;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.model.Role;
import com.cognizant.cms.exception.DataAlreadyExistException;
import com.cognizant.cms.exception.DataNotFoundException;

import com.cognizant.cms.model.RoleView;
import com.cognizant.cms.model.User;
import com.cognizant.cms.repository.RoleRepository;
import com.cognizant.cms.repository.RoleViewRepository;
import com.cognizant.cms.repository.UserRepository;
import com.mongodb.MongoClient;

@TypeAlias("")
@RestController
@RequestMapping("/cms")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository u_repository;
	@Autowired
	RoleRepository r_repository;
	@Autowired
	RoleViewRepository rv_repository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Page<User> getAllUsers(int page, int size) {
		logger.info("Fetching all User API Used");
		Page<User> list = u_repository.findAll(PageRequest.of(page, size));
		return list;
	}

	@RequestMapping(value = "/allUser", method = RequestMethod.GET)
	public List<User> getAllUser() {
		logger.info("Fetching all User API Used");
		List<User> list = u_repository.findAll();
		return list;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> getAllUser(User user) {
		logger.info("Fetching all User API Used");
		User retUser = u_repository.findFirstByUserName(user.getUserName());
		List<User> u = new LinkedList<User>();
		if (retUser != null)
			u.add(retUser);
		return u;

	}

	
	  @RequestMapping(value = "/searchRolename", method = RequestMethod.GET) 
	  public List<RoleView> getRoleByName(String roleName) {
	  logger.info("Fetching searchRolename API Used"); 
	  List<RoleView> roleView =new LinkedList<RoleView>();
			RoleView r=  rv_repository.findFirstByroleName(roleName);
			if(r!=null)  
			roleView.add(r);
	  return roleView; }
	  
	 
//	@RequestMapping(value = "/users", method = RequestMethod.GET)
//	public Page<User> getAllUsers(int page, int size) {
//		logger.info("Fetching all User API Used");
//		Page<User> list = u_repository.findAll(PageRequest.of(page, size));
//		return list;
//	}

//	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
//	@ResponseBody
//	public String create(@Valid @RequestBody User user) {
//
//		logger.info("Create User API Used ");
//		logger.info("Enter : create, User {}", user.getFirstName());
//		User retUser = u_repository.findFirstByUserName(user.getUserName());
//		if (retUser != null)
//		{
//			throw new DataAlreadyExistException();
//		}
//			else
//		{
//			u_repository.save(user);
//			for(Role r:user.getRoles())
//			{
//				RoleView roleView=rv_repository.findFirstByroleName(r.getRoleName());
//				if(roleView!=null)
//				{
//					rv_repository.delete(roleView);
//					roleView.setUserCount(roleView.getUserCount()+1);
//					rv_repository.save(roleView);
//				}
//			}
//		}
//		logger.info("Exit : createUser");
//		return "Successfully Created New User";
//	}
//	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public RoleView create(@Valid @RequestBody User user) {

		logger.info("Create User API Used ");
		logger.info("Enter : create, User {}", user.getFirstName());
		User retUser = u_repository.findFirstByUserName(user.getUserName());
		RoleView roleView = null;
		if (retUser != null) {
			throw new DataAlreadyExistException();
		} else {
			u_repository.save(user);
			for (Role r : user.getRoles()) {
				roleView = rv_repository.findFirstByroleName(r.getRoleName());
				if (roleView != null) {
					MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(), "cms");
					Query query = new Query();
					query.addCriteria(Criteria.where("roleName").is(r.getRoleName()));
					Update update = new Update();
					update.set("userCount", (roleView.getUserCount() + 1));
					mongoTemplate.updateFirst(query, update, RoleView.class);

				}
			}
		}
		logger.info("Exit : createUser");
		return roleView;
	}

	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	@ResponseBody
	public String check(@RequestBody User user) {
		logger.info("Check User API Used ");
		logger.info("Enter : check, User {}", user.getFirstName());
		User retUser = u_repository.findFirstByUserName(user.getUserName());
		if (retUser != null)
			throw new DataAlreadyExistException();

		logger.info("Exit : checkUser");
		return "Successfully Checked New User";

	}

	@RequestMapping(value = "/deleteUser/{userName}", method = RequestMethod.DELETE)
	public List<User> deleteUser(@PathVariable String userName, HttpServletResponse response) {
		logger.info("Delete User API Used");
		logger.info("Deleted data :- " + userName);
		try {
			User user = u_repository.findFirstByUserName(userName);
			u_repository.delete(user);
		} catch (Exception e) {
			logger.error("DeleteUser Data not found :- " + e.getMessage());
			throw new DataNotFoundException();
		}
		logger.info("Exit : DeleteUser");

		return u_repository.findAll();

	}

	@RequestMapping(value = "/updateUser/{userName}", method = RequestMethod.PUT)
	public void modifyUserByName(@PathVariable String userName, @Valid @RequestBody User user) {
		logger.info("Update User API Used");
		logger.info("Updated data :- " + userName);
		User p_user;
		try {
			p_user = u_repository.findFirstByUserName(userName);
		} catch (Exception e) {
			logger.error("UpdateUser Data not found :- " + e.getMessage());
			throw new DataNotFoundException();
		}
		user.set_id(p_user.gett_id());

		System.out.println(user);
		u_repository.save(user);
		logger.info("Exit : UpdateUser");

	}

	@RequestMapping(value = "/countUser", method = RequestMethod.GET)
	public Page<RoleView> getNoUsers(int page, int size) {
		logger.info("Count User API Used");
		Page<RoleView> list = rv_repository.findAll(PageRequest.of(page, size));
		logger.info("Exit : CountUser");
		return list;

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<User> getSerchedData(String searchText) {
		logger.info("Fetching all User API Used");
		User retUser = u_repository.findFirstByUserName(searchText);
		List<User> u = new LinkedList<User>();
		if (retUser != null)
			u.add(retUser);
		return u;

	}
}
