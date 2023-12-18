package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String registPost(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug("/board/regist 호출 -> registPOST()실행");
		bService.registBoard(vo);
		
		rttr.addFlashAttribute("result", "CREATEOK");
		
		return "redirect:/board/listAll";
	}
	
	// 게시판 목록
	// http://localhost:8088/board/listAll
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAllGET(Model model, @ModelAttribute("result") String result, HttpSession session) throws Exception {
		logger.debug("/board/listAll 호출 -> listAllGET()실행");
		session.setAttribute("viewcntCheck", true);
		model.addAttribute("list", bService.getList());
		
		return "/board/listAll";
	}
	
	// 게시글 내용
	// http://localhost:8088/board/content
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void contentGET(BoardVO vo, Model model, HttpSession session) throws Exception {
		logger.debug("/board/content 호출 -> contentGET()실행");
		if((boolean)session.getAttribute("viewcntCheck")) {
			bService.updateViewCount(vo);
			session.setAttribute("viewcntCheck", false);
		}		
		model.addAttribute("vo", bService.getContent(vo));
		if(vo.getBno() == 3) {
			throw new Exception("강제 예외 발생!!!");
		}
	}
	
	// 게시글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(BoardVO vo, Model model) throws Exception {
		logger.debug("/board/modify 호출 -> modifyGET()실행");
		model.addAttribute("vo", bService.getContent(vo));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug("/board/modify 호출 -> modifyPOST()실행");
		bService.updateContent(vo);
		
		rttr.addFlashAttribute("result", "MODIFYOK");
		
		return "redirect:/board/listAll";
	}
	
	// 게시글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET(BoardVO vo, RedirectAttributes rttr) throws Exception {
		logger.debug("/board/delete 호출 -> deleteGET()실행");
		bService.deleteBoard(vo);
		
		rttr.addFlashAttribute("result", "DELETEOK");
		
		return "redirect:/board/listAll";
	}
}
