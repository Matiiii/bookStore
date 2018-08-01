package pl.jstk.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.jstk.constants.ViewNames;

public class AccessDeniedController {

	@GetMapping(value = "books/deleted/accesDenied")
	public String welcome(Model model) {

		return ViewNames.ACCES_DENIED;
	}

}
