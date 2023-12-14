package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;

	@Override
	public void registBoard(BoardVO vo) {
		logger.debug("Service registBoard(BoardVO vo) 호출");
		bdao.insertBoard(vo);		
	}

	@Override
	public List<BoardVO> getList() {
		logger.debug("Service getList() 호출");
		return bdao.selectList();
	}	

	
}
