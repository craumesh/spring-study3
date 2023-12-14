package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bService;
	
	// 글쓰기
	// http://localhost:8088/board/regist
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception {
		logger.debug("/board/regist 호출 -> registGET()실행");
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPost(BoardVO vo) throws Exception {
		logger.debug("/board/regist 호출 -> registPOST()실행");
		bService.registBoard(vo);
		
		return "redirect:/board/listAll";
	}
	
	// http://localhost:8088/board/listAll
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAllGET(Model model) throws Exception {
		logger.debug("/board/listAll 호출 -> listAllGET()실행");
		model.addAttribute("list", bService.getList());
	}
	
}
