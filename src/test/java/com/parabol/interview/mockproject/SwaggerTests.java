package com.parabol.interview.mockproject;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class SwaggerTests {

	@Autowired
	WebApplicationContext context;

	@Test
	public void generateSwagger() throws Exception {
		System.out.println("Generate swagger test");
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
				.accept(MediaType.APPLICATION_JSON))
				.andDo((result -> {
					FileUtils.writeStringToFile(new File("swagger/swagger.json"), result.getResponse().getContentAsString());
				}));
	}
}
