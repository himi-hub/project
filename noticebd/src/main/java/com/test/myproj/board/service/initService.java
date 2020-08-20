package com.test.myproj.board.service;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.myproj.board.service.impl.BoardMapper;

/**
 * @author yurim
 * DDL 초기화 서비스
 */
@Service
public class initService {
	
	private static final Logger logger = LoggerFactory.getLogger(initService.class);
	
	@Resource
	BoardMapper boardMapper;
	
	@PostConstruct
	public void init() throws SQLException {
		logger.info("초기화 메소드=======================================================");
		boardMapper.createTable();
    }
	
}
