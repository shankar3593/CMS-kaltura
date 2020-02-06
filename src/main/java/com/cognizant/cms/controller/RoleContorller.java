package com.cognizant.cms.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cms.exception.DataAlreadyExistException;
import com.cognizant.cms.model.Module;
import com.cognizant.cms.model.Role;
import com.cognizant.cms.model.RoleView;
import com.cognizant.cms.model.User;
import com.cognizant.cms.repository.RoleRepository;
import com.cognizant.cms.repository.RoleViewRepository;
import com.cognizant.cms.repository.UserRepository;
import com.mongodb.MongoClient;

@RestController
@RequestMapping("/cms")
public class RoleContorller {

	private static final Logger logger = LoggerFactory.getLogger(RoleContorller.class);

	@Autowired
	RoleRepository r_repository;
	@Autowired
	RoleViewRepository rv_repository;
	@Autowired
	UserRepository u_repository;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public List<Role> getAllRoles() {
		logger.info("Fetching all Role API Used");
		return r_repository.findAll();
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public List<Role> getUser(Role role) {
		logger.info("Fetching all Role API Used");
		Role retRole = r_repository.findFirstByroleName(role.getRoleName());
		List<Role> r = new LinkedList<Role>();
		if (retRole != null)
			r.add(retRole);
		return r;

	}

	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public List<Module> getModule(Role role) {
		logger.info("Fetching all Role API Used");
		Role retRole = r_repository.findFirstByroleName(role.getRoleName());
		if (retRole != null)
			return retRole.getModules();
		else
			return null;
	}

	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	@ResponseBody
	public @Valid Role createRole(@Valid @RequestBody Role role) {
		logger.info("Enter : create, Role {}", role.getRoleName());
		Role retRole = r_repository.findFirstByroleName(role.getRoleName());
		if (retRole != null)
			throw new DataAlreadyExistException();
		else {
			r_repository.save(role);
			int count = 0;
			RoleView rv = new RoleView();
			rv.setRoleName(role.getRoleName());
			rv.setDescription(role.getDescription());
			rv.setUserCount(0);
			List<Module> modules = role.getModules();
			for (Module m : modules) {
				if (m.getPermissions().isCreate() == true)
					count++;
				if (m.getPermissions().isDelete() == true)
					count++;
				if (m.getPermissions().isModify() == true)
					count++;
				if (m.getPermissions().isView() == true)
					count++;
			}
			rv.setNoOfPermissions(count);
			rv_repository.save(rv);
		}
		logger.info("Exit : createRole");
		return role;

	}

	@RequestMapping(value = "/checkRole", method = RequestMethod.GET)
	@ResponseBody
	public @Valid Role checkRole(Role role) {
		logger.info("Enter : check, Role {}", role.getRoleName());
		Role retRole = r_repository.findFirstByroleName(role.getRoleName());
		if (retRole != null)
			throw new DataAlreadyExistException();
		logger.info("Exit : checkRole");
		return role;
	}

//	@RequestMapping(value = "/deleteRole/{roleName}", method = RequestMethod.DELETE)
//	public List<User> deleteRole(@PathVariable String roleName) {
//		logger.info("Delete Role API Used");
//		logger.info("Deleted data :- " + roleName);
//		List<User> list = null;
//		try {
//			Role role = r_repository.findFirstByroleName(roleName);
//			r_repository.delete(role);
////			RoleView roleView = rv_repository.findFirstByroleName(roleName);
////			rv_repository.delete(roleView);
//			list = u_repository.findRoles(roleName);
//
//			List<Role> roles = null;
//			for (User u : list) {
//
//				roles = u.getRoles();
//				roles.remove(role);
//
//				MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(), "cms");
//				Query query = new Query();
//				query.addCriteria(Criteria.where("userName").is(u.getUserName()));
//				Update update = new Update();
//				update.set("roles", roles);
//				mongoTemplate.updateFirst(query, update, User.class);
//				// mongoTemplate.updateFirst(query, update, RoleView.class);
//
//			}
//
//		} catch (Exception e) {
//			logger.error("DeleteRole Data not found exception :- " + e.getMessage());
//			throw new DataNotFoundException();
//		}
//		logger.info("Exit : DeleteRole");
//
//		return list;
////		return r_repository.findAll();
//
//	}

//	@RequestMapping(value = "/Random", method = RequestMethod.GET)
//	public List<User> getAllUser() {
//		logger.info("Fetching all User API Used");
//
//		List<User> list = u_repository.findRoles("PA");
//
//		return list;
//	}

//	@RequestMapping(value = "/updateRole/{roleName}", method = RequestMethod.PUT)
//	public void modifyRoleByName(@PathVariable("roleName") String roleName, @Valid @RequestBody Role role) {
//		logger.info("Update Role API Used");
//		logger.info("Updated data :- " + roleName);
//		try {
//			Role updated = r_repository.findFirstByroleName(roleName);
//			role.set_id(updated.gett_id());
//			r_repository.save(role);
//		} catch (Exception e) {
//			logger.error("UpdateRole Data not found exception :- " + e.getMessage());
//			throw new DataNotFoundException();
//		}
//		logger.info("Exit : UpdateRole");
//
//	}

	@RequestMapping(value = "/editRole", method = RequestMethod.PUT)
	@ResponseBody
	public List<User> edit(@RequestBody Role role) {

		logger.info("edit role API Used ");

		Role retRole = r_repository.findFirstByroleName(role.getRoleName());
		List<User> users = u_repository.findRoles(role.getRoleName());
		if (retRole != null) {

			MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(), "cms");

			Query query = new Query();
			query.addCriteria(Criteria.where("roleName").is(role.getRoleName()));
			Update update = new Update();
			update.set("modules", (role.getModules()));
			mongoTemplate.updateFirst(query, update, Role.class);

			for (User u : users) {

				Query query1 = new Query();
				query1.addCriteria(Criteria.where("userName").is(u.getUserName()).and("roles")
						.elemMatch(Criteria.where("roleName").is(role.getRoleName())));

				Update update1 = new Update();
				update1.set("roles.$.modules", role.getModules());
				mongoTemplate.updateFirst(query1, update1, User.class);

			}

			int count = 0;

			List<Module> modules = role.getModules();
			for (Module m : modules) {
				if (m.getPermissions().isCreate() == true)
					count++;
				if (m.getPermissions().isDelete() == true)
					count++;
				if (m.getPermissions().isModify() == true)
					count++;
				if (m.getPermissions().isView() == true)
					count++;
			}

			// query.addCriteria(Criteria.where("roleName").is(role.getRoleName()));
			update.set("noOfPermissions", (count));
			mongoTemplate.updateFirst(query, update, RoleView.class);
		}

		logger.info("Exit : createUser");
		retRole = r_repository.findFirstByroleName(role.getRoleName());
		return users;
	}
}
