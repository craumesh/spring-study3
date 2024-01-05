package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping(value = "/boards")
public class BoardRestController {	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
	@Inject
	private BoardService bService;
	
	// 글쓰기 => /boards + 데이터
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> createBoard(@RequestBody BoardVO vo) throws Exception {
		logger.debug("createBoard() 호출");
		logger.debug("BoardVO : "+vo);
		
		bService.registBoard(vo);
		
		return new ResponseEntity<String>("createOK",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{num}", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("num") Integer num) throws Exception {
		logger.debug("readBoard() 호출");
		logger.debug("num : "+num);
		BoardVO vo = new BoardVO();
		vo.setBno(num);
		BoardVO resultVO = bService.getContent(vo);
		
		ResponseEntity<BoardVO> respEntity = null;
		
		if(resultVO != null) {
			respEntity = new ResponseEntity<BoardVO>(resultVO,HttpStatus.OK);
		} else {
			respEntity = new ResponseEntity<BoardVO>(resultVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		try {
//			respEntity = new ResponseEntity<BoardVO>(resultVO,HttpStatus.OK);
//		} catch (Exception e) {
//			respEntity = new ResponseEntity<BoardVO>(resultVO,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
		return respEntity;
	}
}
