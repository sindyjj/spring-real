package com.spring.myweb.snsboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;

@RestController // 비동기로 한다란 의미 restcontroller
@RequestMapping("/snsboard")
@RequiredArgsConstructor
public class SnsBoardController {

	private final SnsBoardService service;
	
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
//		mv.addObject("name","value");
		mv.setViewName("snsboard/snsList");
		return mv;
	}
}