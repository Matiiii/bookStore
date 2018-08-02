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
public class BookController {
	@Autowired
	BookService bookService;

	private static final String INFO_TEXT = "Enjoy newly positions!";
	protected static final String WELCOME = "Capgemini Book Store";

	@GetMapping(value = "/books")
	public String welcome(@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "title", defaultValue = "") String title, Model model) {
		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);

		if ("".equals(author) && "".equals(title)) {

			model.addAttribute("bookList", bookService.findAllBooks());
		} else {
			model.addAttribute("bookList",
					bookService.findBookByManyParametersWithInformation(author, title).getListBookTo());
			model.addAttribute("information",
					bookService.findBookByManyParametersWithInformation(author, title).getInfo());
		}

		return ViewNames.BOOKS;
	}

}
