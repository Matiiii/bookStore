package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.model.Information;
import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;

@Controller
public class AddBookController {

	@Autowired
	BookService bookService;

	private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
	protected static final String WELCOME = "This is a welcome page";

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(value = "/books/add")
	public String viewBook(Model model) {
		model.addAttribute("newBook", new BookTo());
		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);
		return ViewNames.ADD_BOOK;
	}

	@PostMapping(value = "/greeting")
	public String addBook(@ModelAttribute BookTo newBook, Model model) {
		if (!newBook.getAuthors().isEmpty() && !newBook.getTitle().isEmpty()) {
			bookService.saveBook(newBook);
			model.addAttribute("information", new Information("Book is corectly added", true));
		} else {
			model.addAttribute("information", new Information("Requied all fields", false));
		}
		model.addAttribute("newBook", new BookTo());
		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);

		return ViewNames.ADD_BOOK;
	}

}
