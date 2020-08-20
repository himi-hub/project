package com.test.myproj.board.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.myproj.board.service.BoardService;

@Service("boardService2")
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Resource
	BoardMapper boardMapper;

	@Override
	public int getCntBoard() throws IOException, SQLException{
		return boardMapper.getCntBoard();
	}
	
	@Override
	public List<?> selectBoardList(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.selectBoardList(commandMap);
	}

	@Override
	public Map<String, Object> selectBoardInfo(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.selectBoardInfo(commandMap);
	}

	@Override
	public int insertBoardProc(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.insertBoardProc(commandMap);
	}

	@Override
	public int updateBoardProc(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.updateBoardProc(commandMap);
	}

	@Override
	public int deleteBoardProc(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.deleteBoardProc(commandMap);
	}

	@Override
	public int insertFileProc(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.insertFileProc(commandMap);
	}

	@Override
	public List<?> selectFileList(Map<String, Object> commandMap) throws IOException, SQLException {
		return boardMapper.selectFileList(commandMap);
	}

	
}
