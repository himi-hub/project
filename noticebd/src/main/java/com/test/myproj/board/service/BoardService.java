package com.test.myproj.board.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {

	/**
	 * 전체 공지글수
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int getCntBoard() throws IOException, SQLException;
	
	/**
	 * 공지글 목록
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	List<?> selectBoardList(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * 공지글 상세 정보
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	Map<String, Object> selectBoardInfo(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * 공지글 등록
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int insertBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;

	/**
	 * 공지글 수정
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int updateBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;

	/**
	 * 공지글 삭제
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int deleteBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * 파일 등록
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int insertFileProc(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * 파일 목록
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	List<?> selectFileList(Map<String, Object> commandMap) throws IOException, SQLException;
	
}
