package com.shiro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "/index";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "403";
	}

}