package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	public void registBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> getList() throws Exception;
	
	public BoardVO getContent(BoardVO vo) throws Exception;
	
	public void updateContent(BoardVO vo) throws Exception;
	
	public void updateViewCount(BoardVO vo) throws Exception;
	
	public void deleteBoard(BoardVO vo) throws Exception;
}
