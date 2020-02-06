package com.cognizant.cms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.cms.repository.ModuleRepository;
import com.cognizant.cms.model.Module;
import com.cognizant.cms.model.Permission;

@RestController
@RequestMapping("/cms")
public class ModuleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);
	
	@Autowired
	ModuleRepository m_repository;
	
	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public List<Module> getAllModule() {
		logger.info("Fetching modules API Used");
		return m_repository.findAll();
	}

	@RequestMapping(value = "/modulesave", method = RequestMethod.GET)
	public Module saveModule() {
		logger.info("Fetching modulesave API Used");
		Module module=new Module();
		module.setModuleName("Home");
		Permission permission=new Permission();
		module.setPermissions(permission);
		 m_repository.save(module);
		 module.setModuleName("Role");
		 module.setPermissions(permission);
		 m_repository.save(module);
		 module.setModuleName("User");
		 module.setPermissions(permission);
		 m_repository.save(module);
		 module.setModuleName("Other");
		 module.setPermissions(permission);
		 m_repository.save(module);
		 return null;
	}
}
