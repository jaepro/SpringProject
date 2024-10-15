package com.controller.SpringProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //이거 작성했으면 xml에 빈 설정해야 함!!
public class MainController {
	@RequestMapping(value="/")
	public String index() {
		return "/index";
	}

}
