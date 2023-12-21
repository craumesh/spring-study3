package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;

	@Override
	public void registBoard(BoardVO vo) throws Exception {
		logger.debug("Service registBoard(BoardVO vo) 호출");
		bdao.insertBoard(vo);		
	}

	@Override
	public List<BoardVO> getList() throws Exception {
		logger.debug("Service getList() 호출");
		return bdao.selectList();
	}

	@Override
	public BoardVO getContent(BoardVO vo) throws Exception {
		logger.debug("Service getContent() 호출");
		return bdao.selectContent(vo);
	}

	@Override
	public void updateContent(BoardVO vo) throws Exception {
		logger.debug("Service updateContent(BoardVO vo) 호출");
		bdao.updateContent(vo);
	}

	@Override
	public void updateViewCount(BoardVO vo) throws Exception {
		logger.debug("Service updateContent(BoardVO vo) 호출");
		bdao.updateViewCount(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		logger.debug("Service deleteBoard(BoardVO vo) 호출");
		bdao.deleteBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception {
		logger.debug("Service getBoardListPage() 호출");
		return bdao.getBoardListPage(cri);
	}

	@Override
	public int getTotalCount() throws Exception {
		logger.debug("Service getTotalCount() 호출");
		return bdao.getTotalCount();
	}
}
