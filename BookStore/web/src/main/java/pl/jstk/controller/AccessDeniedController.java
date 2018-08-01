package pl.jstk.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;

public class AccessDeniedController {

	private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
	protected static final String WELCOME = "This is a welcome page";

	@GetMapping(value = "books/deleted/accesDenied")
	public String welcome(Model model) {
		model.addAttribute(ModelConstants.MESSAGE, WELCOME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);
		return ViewNames.ACCES_DENIED;
	}

}
