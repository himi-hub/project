package com.test.myproj.board.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {

	/**
	 * 전체 공지글수
	 * @return
	 * @throws SQLException
	 */
	int getCntBoard() throws SQLException;
	
	/**
	 * 공지글 목록
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	List<?> selectBoardList(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * 공지글 상세 정보
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectBoardInfo(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * 공지글 등록
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int insertBoardProc(Map<String, Object> commandMap) throws SQLException;

	/**
	 * 공지글 수정
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int updateBoardProc(Map<String, Object> commandMap) throws SQLException;

	/**
	 * 공지글 삭제
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int deleteBoardProc(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * DB 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	int createTable() throws SQLException;

	/**
	 * 파일 등록
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int insertFileProc(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * 파일 목록
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	List<?> selectFileList(Map<String, Object> commandMap) throws SQLException;
	
	
}
