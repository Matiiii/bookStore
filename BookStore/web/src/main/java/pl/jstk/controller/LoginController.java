package pl.jstk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.jstk.constants.ViewNames;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String welcome(@RequestParam(value = "error", defaultValue = "") String error, Model model) {

		model.addAttribute("error", error);

		return ViewNames.LOGIN;
	}

}
