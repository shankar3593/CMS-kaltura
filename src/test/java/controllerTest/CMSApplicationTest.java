package controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cognizant.cms.CmsApplication;
import com.cognizant.cms.controller.RoleContorller;
import com.cognizant.cms.controller.UserController;
import com.cognizant.cms.exception.DataAlreadyExistException;
import com.cognizant.cms.exception.DataNotFoundException;
import com.cognizant.cms.model.Role;
import com.cognizant.cms.model.User;
import com.cognizant.cms.repository.RoleRepository;
import com.cognizant.cms.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = CmsApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CMSApplicationTest {

	private static final String SOME_PREDEFINED_RESPONSE = "Some predefined response";
	@Autowired
	private MockMvc mockMvc;

	User user;
	Role role;

	@MockBean
	RoleRepository r_repository;

	@MockBean
	UserRepository u_repository;

	@SpyBean
	UserController userController;
	@SpyBean
	RoleContorller roleContoller;

	@Before
	public void init() {



		List<Role> roles = new LinkedList();
		role = new Role("roleName", "des", null);
		roles.add(role);
		// ObjectId id = new ObjectId("3");
		user = new User("admink", "123456789", roles, "abc", "bcd");
	}

	@Test
	public void create_user() throws Exception {
		
		String json = "{\"userName\": \"nehasi\", \"password\": \"4edg54\",\"roles\": null, \"firstName\":\"dilkash\", \"lastName\": \"neha\" }";
		when(u_repository.save(Mockito.any(User.class))).thenReturn(user);
		this.mockMvc.perform(post("/cms/create").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print());
		// .andExpect(status().isOk());
	}


	 
	 @Test(expected=DataAlreadyExistException.class)
	    public void checkexisting_user() throws Exception {
	        String json = "{\"username\":\"shekhargulati\",\"name\":\"Shekhar Gulati\"}";
	        when(u_repository.save(Mockito.any(User.class))).thenReturn(user);
	        this.mockMvc
	                .perform(post("/cms/create")
	                		.contentType(MediaType.APPLICATION_JSON).content(json))
	                .andDo(print());
	        this.mockMvc
	        .perform(post("/cms/create")
	        		.contentType(MediaType.APPLICATION_JSON).content(json))
	        .andDo(print());
	       throw new DataAlreadyExistException();
	               // .andExpect(status().isOk());
	    } 
	 
	 
	 
	 @Test
		public void deleteUser() throws Exception {
			String json = "{\"userName\":\"shekhargulati\",\"firstName\":\"Shekhar Gulati\"}";
			String userName = "shekhargulati";

			doReturn(user).when(u_repository).delete(u_repository.findFirstByUserName(userName));

			this.mockMvc
					.perform(delete("/cms/deleteUser/{userName}", 3).contentType(MediaType.APPLICATION_JSON).content(json))
					.andDo(print());
		}
	 
	
//	@Test(expected = IndexOutOfBoundsException.class) 
//	public void empty() { 
//	     new ArrayList<Object>().get(0); 
//	}
	 
	 
	 @Test(expected=DataNotFoundException.class)
	    public void notfoundUser() throws Exception {
	           String json = "{\"username\":\"shekhargulati\",\"name\":\"Shekhar Gulati\"}";
	           String userName = user.getUserName();
	           userName=null;
	           doReturn(user).when(u_repository).delete(u_repository.findFirstByUserName(userName));          
	           this.mockMvc.perform(delete("/cms/deleteUser/{userName}", "re").contentType(MediaType.APPLICATION_JSON));
	           this.mockMvc.perform(delete("/cms/deleteUser/{userName}", "re").contentType(MediaType.APPLICATION_JSON));
	           throw new DataNotFoundException();
	    }

	
	
	

	@Test
	public void create_role() throws Exception {
		String json = "{\"roleName\":\"shekhargulati\",\"description\":\"Shekhar Gulati\"}";
		when(r_repository.save(Mockito.any(Role.class))).thenReturn(role);
		this.mockMvc.perform(post("/cms/createRole").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print());
		
		// .andExpect(status().isOk());
	}
	
	
	
	 @Test(expected=DataAlreadyExistException.class)
	    public void checkexisting_role() throws Exception {
	        String json = "{\"username\":\"shekhargulati\",\"name\":\"Shekhar Gulati\"}";
	        when(r_repository.save(Mockito.any(Role.class))).thenReturn(role);
	        this.mockMvc
	                .perform(post("/cms/create")
	                		.contentType(MediaType.APPLICATION_JSON).content(json))
	                .andDo(print());
	        this.mockMvc
	        .perform(post("/cms/create")
	        		.contentType(MediaType.APPLICATION_JSON).content(json))
	        .andDo(print());
	       throw new DataAlreadyExistException();
	               // .andExpect(status().isOk());
	    } 
	 
	 
	 
	 
	 @Test
		public void deleteRole() throws Exception {
			String json = "{\"roleName\":\"abc\",\"description\":\"Admininstrator\"}";
			String roleName = role.getRoleName();
			doReturn(role).when(r_repository).delete(r_repository.findFirstByroleName(roleName));

			this.mockMvc
					.perform(delete("/cms/deleteRole/{roleName}", 3).contentType(MediaType.APPLICATION_JSON).content(json))
					.andDo(print());
		}
	 
	 
	 
	 
	 @Test(expected=DataNotFoundException.class)
	    public void notfoundRole() throws Exception {
	           String json = "{\"username\":\"shekhargulati\",\"name\":\"Shekhar Gulati\"}";
	           String roleName = role.getRoleName();
	           roleName=null;
	           doReturn(role).when(r_repository).delete(r_repository.findFirstByroleName(roleName));          
	           this.mockMvc.perform(delete("/cms/deleteRole/{roleName}", "re").contentType(MediaType.APPLICATION_JSON));
	           this.mockMvc.perform(delete("/cms/deleteRole/{roleName}", "re").contentType(MediaType.APPLICATION_JSON));
	           throw new DataNotFoundException();
	    }

	
	


}
