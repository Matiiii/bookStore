package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.service.BookService;

@Controller
public class OneBookController {
	@Autowired
	BookService bookService;

	private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
	protected static final String WELCOME = "This is a welcome page";

	@GetMapping(value = "/books/book")
	public String viewOneBookPage(@RequestParam(value = "id", defaultValue = "") Long id, Model model) {

		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);

		model.addAttribute("book", bookService.getOneById(id));
		return ViewNames.BOOK;
	}

}
