package com.test.myproj.board.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {

	/**
	 * ��ü �����ۼ�
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int getCntBoard() throws IOException, SQLException;
	
	/**
	 * ������ ���
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	List<?> selectBoardList(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * ������ �� ����
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	Map<String, Object> selectBoardInfo(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * ������ ���
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int insertBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;

	/**
	 * ������ ����
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int updateBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;

	/**
	 * ������ ����
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int deleteBoardProc(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * ���� ���
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	int insertFileProc(Map<String, Object> commandMap) throws IOException, SQLException;
	
	/**
	 * ���� ���
	 * @param commandMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	List<?> selectFileList(Map<String, Object> commandMap) throws IOException, SQLException;
	
}
