package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	public void insertBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> selectList() throws Exception;
	
	public BoardVO selectContent(BoardVO vo) throws Exception;
	
	public void updateContent(BoardVO vo) throws Exception;
	
	public void updateViewCount(BoardVO vo) throws Exception;
	
	public void deleteBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> getBoardListPage(int page) throws Exception;
	
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception;
	
	public int getTotalCount() throws Exception;
}
