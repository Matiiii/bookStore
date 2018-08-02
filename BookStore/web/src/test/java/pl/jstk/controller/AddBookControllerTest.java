package pl.jstk.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.jstk.constants.ViewNames;
import pl.jstk.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class AddBookControllerTest {

	@Autowired
	BookService bookService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	AddBookController addbookController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(new AddBookController()).build();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldShowsBook() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/book/2"));
		// then
		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.BOOK));

	}

	@Test
	@WithMockUser(username = "john", roles = { "" })
	public void shouldGive403Exception() {
		// given

		// when
		boolean exceptionTrows = false;
		ResultActions resultActions;

		try {
			resultActions = mockMvc.perform(get("/books/add"));
		} catch (Exception e) {

			exceptionTrows = true;
		}

		// then
		assertTrue(exceptionTrows);
		// resultActions.andExpect(status().is4xxClientError()).andExpect(view().name(ViewNames.LOGIN));
	}

	@Test
	@WithMockUser(username = "john", roles = { "USER" })
	public void shouldSchowAddBookView() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/add"));

		// then

		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.ADD_BOOK));
	}

	@Test
	@WithMockUser(username = "john", roles = { "USER" })
	public void shouldAddBook() throws Exception {
		// given

		// when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
				.param("title", "Jak zrobic post request test").param("authors", "Mateusz Kaczmarek")
				.param("status", "LOAN").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk()).andExpect(view().name(ViewNames.ADD_BOOK));
	}

}