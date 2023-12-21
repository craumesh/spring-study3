package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.service.BoardService;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Override
	public void insertBoard(BoardVO vo) {
		logger.debug("DAO insertBoard(BoardVO vo) 호출");
		sqlSession.insert(NAMESPACE+".insertBoard", vo);
	}

	@Override
	public List<BoardVO> selectList() {
		logger.debug("DAO selectList() 호출");
		return sqlSession.selectList(NAMESPACE+".selectList");
	}

	@Override
	public BoardVO selectContent(BoardVO vo) throws Exception {
		logger.debug("DAO selectContent() 호출");
		return sqlSession.selectOne(NAMESPACE+".selectContent", vo);
	}

	@Override
	public void updateContent(BoardVO vo) throws Exception {
		logger.debug("DAO updateBoard(BoardVO vo) 호출");
		sqlSession.update(NAMESPACE+".updateContent", vo);
	}

	@Override
	public void updateViewCount(BoardVO vo) throws Exception {
		logger.debug("DAO updateViewCount(BoardVO vo) 호출");
		sqlSession.update(NAMESPACE+".updateViewCount", vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) throws Exception {
		logger.debug("DAO deleteBoard(BoardVO vo) 호출");
		sqlSession.update(NAMESPACE+".deleteBoard", vo);
	}

	@Override
	public List<BoardVO> getBoardListPage(int page) throws Exception {
		logger.debug("DAO getBoardListPage() 호출");
		
		// 페이징처리 계산
		// page 1 => 1~10 => limit 0,10
		// page 2 => 11~20 => limit 10,10
		// (page-1)*10
		
		return sqlSession.selectList(NAMESPACE+".listPage", (page-1)*10);
	}	
	
	@Override
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception {
		logger.debug("DAO getBoardListPage() 호출");
		
		// 페이징처리 계산
		// page 1 => 1~10 => limit 0,10
		// page 2 => 11~20 => limit 10,10
		// (page-1)*10
		
		return sqlSession.selectList(NAMESPACE+".listPage", cri);
	}

	@Override
	public int getTotalCount() throws Exception {
		logger.debug("DAO getTotalCount() 호출");
		return sqlSession.selectOne(NAMESPACE+".totalCount");
	}	
	
}
