package com.itwillbs.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBConnectTest {	
	private static final Logger logger = LoggerFactory.getLogger(DBConnectTest.class);
	
	@Inject
	private SqlSession sqlSession;
	
	@Inject
	private BoardDAO bdao;
	
//	@Test
	public void 디비연결테스트() {
		logger.debug("디비 연결 : "+sqlSession);
	}
	
	@Test
	public void 페이징처리() throws Exception {
		Criteria cri = new Criteria();
//		for(BoardVO vo:bdao.getBoardListPage(2)) {
//			logger.debug(vo.getBno()+" || "+vo.getTitle()+" || "+vo.getContent());
//		}
		
		cri.setPage(2);
		cri.setPageSize(20);
		
		for(BoardVO vo:bdao.getBoardListPage(cri)) {
			logger.debug(vo.getBno()+" || "+vo.getTitle());
		}
		
	}
}
