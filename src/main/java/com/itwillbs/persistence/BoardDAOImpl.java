package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
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
	
}
