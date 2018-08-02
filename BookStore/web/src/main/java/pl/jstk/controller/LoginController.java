package pl.jstk.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.jstk.constants.ViewNames;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String showLoginPage(@RequestParam(value = "error", defaultValue = "") String error, Model model) {

		if (error.equals("true")) {
			model.addAttribute("error", error);
		}

		return ViewNames.LOGIN;
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public String accessDenied(Model model) {
		model.addAttribute("error", "Access denied. You are not allowed to delete book");
		return ViewNames.ACCES_DENIED;
	}

}
