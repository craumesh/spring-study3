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
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
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
	
	
	/**
	 * 페이징처리
	 * 0) 반드시 GET방식으로만 처리
	 * 1) 원하는 만큼의 데이터를 가져와서 출력
	 * 2) 페이지 블럭 생성
	 * 3) 본문/수정/삭제등.. 처리 후 리스트 이동시
	 * 	기존의 정보를 유지
	 * 
	 * a태그 : 네이버 쇼핑 / 유사한 코드의 반복적인 동작 수행
	 * 	=> 검색엔진 노출이 쉬움
	 * 
	 * form태그 : 쿠팡 / input태그를 사용해서 처리
	 * 	=> 데이터처리 (빠른 처리)
	 * 
	 * - 하단 페이징블럭
	 * 1) 시작페이지 번호
	 * 2) 끝페이지 번호
	 * 3) 전체 데이터 개수
	 * 4) 이전페이지 링크(boolean)
	 * 5) 다음페이지 링크(boolean)
	 * 
	 * ex) 총 122개 / 페이지당 10개씩 출력
	 * 	- 1페이지 : 시작페이지 1, 끝페이지 10, 이전버튼X, 다음버튼O
	 *  - 7페이지 : 시작페이지 1, 끝페이지 10, 이전버튼X, 다음버튼O
	 *  - 12페이지 : 시작페이지 11, 끝페이지 13, 이전버튼O, 다음버튼X
	 */	
	// http://localhost:8088/board/listPage?page=11&pageSize=10
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPageGET(Model model, @ModelAttribute("result") String result, HttpSession session, Criteria cri) throws Exception {
		logger.debug("/board/listPage 호출 -> listPageGET()실행");
		session.setAttribute("viewcntCheck", true);
//		Criteria cri = new Criteria();
//		cri.setPage(1);
//		cri.setPageSize(20);
		
		// 페이지 블럭 정보 준비 -> view 페이지 전달
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(bService.getTotalCount());
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", bService.getBoardListPage(cri));
		
		return "/board/listAll";
	}
}
