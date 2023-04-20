package com.example.demo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.domain.Cat;
import com.fasterxml.jackson.databind.ObjectMapper;

// This annotation will actually run your app
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// Script here is an array.
@Sql(scripts = { "classpath:cat-schema.sql", "classpath:cat-data.sql" })
public class CatIntegrationTest {

	// Test classes don't typically use constructors therefore:
	@Autowired
	private MockMvc mvc;

	// Convert to JSON
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Cat newCat = new Cat(true, "rat", true, 1);
		// writeValueAsString throws exception, so the method needs "throws Exception"
		String newCatAsJson = this.mapper.writeValueAsString(newCat);
		RequestBuilder req = MockMvcRequestBuilders.post("/create").content(newCatAsJson)
				.contentType(MediaType.APPLICATION_JSON);

		// Short form way, see imports
		ResultMatcher checkStatus = status().isCreated();
		Cat created = new Cat(2L, true, "rat", true, 1);
		String createdAsJson = this.mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(createdAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetById() throws Exception {
		this.mvc.perform(get("/get/1")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(new Cat(1L, true, "rat", true, 1))));
	}

	@Test
	void testGetAll() throws Exception {
		List<Cat> cats = new ArrayList<>();
		cats.add(new Cat(true, "rat", true, 1));

		String createdAsJson = this.mapper.writeValueAsString(cats);
		ResultMatcher checkBody = content().json(createdAsJson);

		RequestBuilder req = MockMvcRequestBuilders.get("/get").content(createdAsJson)
				.contentType(MediaType.APPLICATION_JSON);
	}

	@Test
	void testUpdate() throws Exception {
		int id = 1;
		RequestBuilder req = patch("/update/" + id).param("evil", "true").param("hasWhiskers", "false")
				.param("name", "rat").param("length", "1");
		ResultMatcher checkResultMatcher = status().isOk();
		Cat resultCat = new Cat(1L, true, "rat", true, 1);
		String resultCatAsJson = mapper.writeValueAsString(resultCat);
		ResultMatcher checkBody = content().json(resultCatAsJson);
		this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		int Id = 1;
		RequestBuilder req = delete("/remove/" + Id);

		ResultMatcher checkResultMatcher = status().isOk();
		Cat resultCat = new Cat(1L, true, "rat", true, 1);
		String resultCatAsJson = mapper.writeValueAsString(resultCat);
		ResultMatcher checkBody = content().json(resultCatAsJson);
		this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
	}

	@Test
	void testNotFound() throws Exception {
		int id = 2;
		RequestBuilder req = get("/get/" + id);
		ResultMatcher checkResultMatcher = status().isNotFound();
		this.mvc.perform(req).andExpect(checkResultMatcher);
	}

}
