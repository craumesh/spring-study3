package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	public void registBoard(BoardVO vo);
	
	public List<BoardVO> getList();
}
