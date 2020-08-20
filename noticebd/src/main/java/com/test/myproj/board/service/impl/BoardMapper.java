package com.test.myproj.board.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BoardMapper {

	/**
	 * ��ü �����ۼ�
	 * @return
	 * @throws SQLException
	 */
	int getCntBoard() throws SQLException;
	
	/**
	 * ������ ���
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	List<?> selectBoardList(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * ������ �� ����
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectBoardInfo(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * ������ ���
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int insertBoardProc(Map<String, Object> commandMap) throws SQLException;

	/**
	 * ������ ����
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int updateBoardProc(Map<String, Object> commandMap) throws SQLException;

	/**
	 * ������ ����
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int deleteBoardProc(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * DB ���̺� ����
	 * @return
	 * @throws SQLException
	 */
	int createTable() throws SQLException;

	/**
	 * ���� ���
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	int insertFileProc(Map<String, Object> commandMap) throws SQLException;
	
	/**
	 * ���� ���
	 * @param commandMap
	 * @return
	 * @throws SQLException
	 */
	List<?> selectFileList(Map<String, Object> commandMap) throws SQLException;
	
	
}
