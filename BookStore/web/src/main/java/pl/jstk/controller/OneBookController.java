package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.model.Information;
import pl.jstk.service.BookService;

@Controller
public class OneBookController {
	@Autowired
	BookService bookService;

	private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
	protected static final String WELCOME = "This is a welcome page";

	@GetMapping(value = "/books/book/{id}")
	public String viewOneBookPage(@PathVariable Long id, Model model) {

		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);

		model.addAttribute("book", bookService.getOneById(id));
		return ViewNames.BOOK;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/books/book/{id}")
	public String deleteBookButton(@PathVariable Long id, Model model) {
		bookService.deleteBook(id);
		model.addAttribute("information", new Information("Book is corectly deleted", true));
		model.addAttribute("bookList", bookService.findAllBooks());
		return ViewNames.BOOKS;
	}

}
