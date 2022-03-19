package com.nnk.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;

@WebMvcTest(controllers = UserController.class)
//@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void getUserList() {

		User user = new User(1, "testUsername", "testPassword", "testFullname", "USER");

		try {
			mvc.perform(get("/user/list").contentType(MediaType.ALL_VALUE).characterEncoding("utf-8"))
					.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn().getResponse()
					.getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void addUserTest() {

		User test = new User(1, "testUsername", "testPassword", "testFullname", "USER");

		try {
			mvc.perform(get("/user/add").param("user", test)).andDo(MockMvcResultHandlers.print())
					.andExpect(status().isOk()).andReturn().getResponse().getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void validateUserTest() {

	}

	@Test
	void updateUserTest() {

	}

	@Test
	void deleteUserTest() {

	}

}
