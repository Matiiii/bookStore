package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.model.Information;
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

	@DeleteMapping(value = "/books/deleteBook")
	public String deleteBookButton2(@RequestParam("id") Long id, Model model) {
		bookService.deleteBook(id);
		model.addAttribute("information", new Information("Book is corectly deleted", true));
		model.addAttribute("bookList", bookService.findAllBooks());
		return ViewNames.BOOKS;
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public String accessDenied(Model model) {
		model.addAttribute("error", "Access denied. You are not allowed to delete book");
		return ViewNames.ACCES_DENIED;
	}

}
