package com.sp.sp.test.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.sp.test.vo.TestVo;
@Controller
public class thymeleafController {
	@RequestMapping("/thymeleafTest")
	public String thymeleafTest(Model model) {
		TestVo testModel = new TestVo("this is id","재문");
		model.addAttribute("testModel", testModel);
		return "thymeleaf/thymeleafTest";
	}
}
