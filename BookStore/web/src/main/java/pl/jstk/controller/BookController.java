package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.model.Information;
import pl.jstk.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;

	private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
	protected static final String WELCOME = "This is a welcome page";

	@GetMapping(value = "/books")
	public String welcome(@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "title", defaultValue = "") String title, Model model) {
		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);

		if ("".equals(author) && "".equals(title)) {

			model.addAttribute("bookList", bookService.findAllBooks());
		} else if ("".equals(title) && !("".equals(author))) {

			if (bookService.findBooksByAuthor(author).isEmpty()) {
				model.addAttribute("information", new Information("Not found any books writed by: " + author, false));
			} else {

				String books = (bookService.findBooksByAuthor(author).size() == 1) ? " book" : " books";
				model.addAttribute("information",
						new Information("Found " + bookService.findBooksByAuthor(author).size() + books, true));
			}

			model.addAttribute("bookList", bookService.findBooksByAuthor(author));

		} else if ("".equals(author) && !("".equals(title))) {
			if (bookService.findBooksByTitle(title).isEmpty()) {
				model.addAttribute("information", new Information("Not found any books like: " + title, false));
			} else {

				String books = (bookService.findBooksByTitle(title).size() == 1) ? " book" : " books";

				model.addAttribute("information",
						new Information("Found " + bookService.findBooksByTitle(title).size() + books, true));
			}
			model.addAttribute("bookList", bookService.findBooksByTitle(title));
		} else if (!("".equals(author) && "".equals(title))) {
			model.addAttribute("information", new Information("Only one field required", false));
			model.addAttribute("bookList", bookService.findAllBooks());
		}

		return ViewNames.BOOKS;
	}

	@PostMapping(value = "/books/deleteBook")
	public String deleteBookButton2(@RequestParam("id") Long id, Model model) {
		bookService.deleteBook(id);
		model.addAttribute("information", new Information("Book is corectly deleted", true));
		model.addAttribute("bookList", bookService.findAllBooks());
		return ViewNames.BOOKS;
	}

	/*
	 * @ExceptionHandler({ AccessDeniedException.class }) public String
	 * accessDenied(Model model) { model.addAttribute("error",
	 * "Access denied. You are not allowed to delete book"); return
	 * ViewNames.ACCES_DENIED; }
	 */

}
