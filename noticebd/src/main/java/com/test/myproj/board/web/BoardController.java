package com.test.myproj.board.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.myproj.board.service.BoardService;
import com.test.myproj.board.service.PagingVO;

/**
 * @author yurim
 * @파일정보 : 공지관리 Controller
 * 
 */
@Controller
public class BoardController {
	
	/** 공지관리 서비스 */
	@Resource(name = "boardService2")
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	/**
	 * 공지글 목록 페이지
	 * @param model
	 * @param commandMap
	 * @param vo
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	@RequestMapping(value = {"/","/board/boardList.do"})
	public String boardList(Model model, @RequestParam Map<String, Object> commandMap, PagingVO vo) throws IOException, SQLException {
		
		int totalCnt = boardService.getCntBoard();
		vo = new PagingVO(totalCnt, vo.getNowPage(), vo.getCntPerPage());
		model.addAttribute("paging", vo);
		
		commandMap.put("firstIndex", vo.getStart());
		commandMap.put("lastIndex", vo.getEnd());
		List boardList = boardService.selectBoardList(commandMap);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	/**
	 * 공지글 상세조회/수정 페이지
	 * @param model
	 * @param paramMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/board/boardEdit.do")
	public String boardEdit (Model model, @RequestParam Map<String, Object> paramMap) throws IOException, SQLException, RuntimeException {

		Map<String, Object> boardInfo = boardService.selectBoardInfo(paramMap);
		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("paramMap", paramMap);
		
		if(boardInfo.get("FILE_ID") != null) {
			paramMap.put("file_id", boardInfo.get("FILE_ID"));
			List fileList = boardService.selectFileList(paramMap);
			model.addAttribute("fileList", fileList);
		}
		
		return "board/boardEdit";
	}
	
	/**
	 * 공지글 수정 프로세스
	 * @param model
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/board/boardEditProc.do", method=RequestMethod.POST)
	public String boardEditProc (Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws IOException, SQLException, RuntimeException{

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;

		try {
			/* 파일저장 S================================ */
			//파일 저장경로
			String path = request.getSession().getServletContext().getRealPath("");
		   	path = new java.io.File(new java.io.File(path).getParent()) + System.getProperty("file.separator") + "upload_data";
			
		   	int i = 0;
		   	boolean fileYn = false;
		   	Map<String, MultipartFile> files = multiRequest.getFileMap();
		   	if(files != null) {
		   		paramMap.put("file_id", idMake());
			   	Iterator<Entry<String, MultipartFile>> fileIter = files.entrySet().iterator();
			   	while ( fileIter.hasNext() ){
			   		Entry<String, MultipartFile> entry = fileIter.next();
					HashMap<?, ?> hmFile = fileUpload(files, entry.getKey(), "board", path);
					if(hmFile != null) {
						i++;
						fileYn = true;
						paramMap.put("file_seq", i);
						paramMap.put("file_name", (String)hmFile.get("F_SAVENAME"));
						boardService.insertFileProc(paramMap);
					}
				}
		   	}
		   	if(!fileYn) {
		   		String file_old = multiRequest.getParameter("file_old");
		   		if(file_old != "") paramMap.put("file_id", file_old);
		   	}
		   	/* 파일저장 E================================ */
		   	
			int result = boardService.updateBoardProc(paramMap);
			model.addAttribute("alertMsg", "수정되었습니다.");
			model.addAttribute("gotoUrl", "/board/boardEdit.do?bd_idx="+paramMap.get("bd_idx")+"&nowPage="+paramMap.get("nowPage")+"&cntPerPage="+paramMap.get("cntPerPage"));
			
		} catch (Exception e) {
			model.addAttribute("alertMsg", "에러가 발생하였습니다. 잠시후에 다시 이용하세요.");
		}
		
		return "common/alert";
	}
	
	/**
	 * 공지글 등록 페이지
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/board/boardRegist.do")
	public String boardRegist() throws IOException, SQLException, RuntimeException {

		return "board/boardRegist";
	}
	
	/**
	 * 공지글 등록 프로세스
	 * @param model
	 * @param paramMap
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/board/boardRegistProc.do", method=RequestMethod.POST)
	public String boardRegistProc (Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws IOException, SQLException, RuntimeException{

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;

		try {
			/* 파일저장 S================================ */
			//파일 저장경로
			String path = request.getSession().getServletContext().getRealPath(""); 
			path = new java.io.File(new java.io.File(path).getParent()) + System.getProperty("file.separator") + "upload_data"; 
			
		   	int i = 0;
		   	Map<String, MultipartFile> files = multiRequest.getFileMap();
		   	if(files != null) {
		   		paramMap.put("file_id", idMake());
			   	Iterator<Entry<String, MultipartFile>> fileIter = files.entrySet().iterator();
			   	while ( fileIter.hasNext() ){
			   		Entry<String, MultipartFile> entry = fileIter.next();
					HashMap<?, ?> hmFile = fileUpload(files, entry.getKey(), "board", path);
					if(hmFile != null) {
						i++;
						paramMap.put("file_seq", i);
						paramMap.put("file_name", (String)hmFile.get("F_SAVENAME"));
						boardService.insertFileProc(paramMap);
					}
				}
		   	}
		   	/* 파일저장 E================================ */
		   	
		   	int result = boardService.insertBoardProc(paramMap);
		   	model.addAttribute("alertMsg", "등록되었습니다.");
			model.addAttribute("gotoUrl", "/board/boardList.do");
			
		} catch (Exception e) {
			model.addAttribute("alertMsg", "에러가 발생하였습니다. 잠시후에 다시 이용하세요.");
		}
		
		return "common/alert";
	}
	
	/**
	 * 공지글 삭제 프로세스
	 * @param model
	 * @param paramMap
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/board/boardDeleteProc.do", method=RequestMethod.POST)
	public String boardDeleteProc (Model model, @RequestParam Map<String, Object> paramMap) throws IOException, SQLException, RuntimeException{

		try {
			int result = boardService.deleteBoardProc(paramMap);
			model.addAttribute("alertMsg", "삭제되었습니다.");
			model.addAttribute("gotoUrl", "/board/boardList.do");
		} catch (Exception e) {
			model.addAttribute("alertMsg", "에러가 발생하였습니다. 잠시후에 다시 이용하세요.");
		}
		
		return "common/alert";
	}
	
	/**
	 * 파일 업로드
	 * @param files
	 * @param fieldName
	 * @param storePath
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public HashMap fileUpload(Map<String, MultipartFile> files, String fieldName, String storePath, String path) throws IOException {
		
		storePath = path + System.getProperty("file.separator")  + storePath;
		File saveFolder = new File(storePath);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.setExecutable(false, true);
			saveFolder.setReadable(true);
			saveFolder.setWritable(true, true);
		    saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		HashMap hm = null;

		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();
		    file = entry.getValue();
		    long _size = file.getSize();
		    
		    if(_size > 0) {
			    String originalFileName = file.getOriginalFilename();
	
			    if(fieldName.equals(file.getName())){
			    	hm = new HashMap();
			    	int index = originalFileName.lastIndexOf(".");
				    String fileExt = originalFileName.substring(index + 1);
	
				    filePath = storePath + File.separator + originalFileName;
					file.transferTo(new File(filePath));
	
				    hm.put("F_SAVENAME", originalFileName);
				    hm.put("F_EXT", fileExt);
				    hm.put("F_FILESIZE", _size);
				    hm.put("F_ORGNAME", originalFileName);
			    }
		    }
		}

		return hm;
	}
	
	/**
	 * 파일아이디 생성
	 * @return
	 */
	public String idMake() {
		Random random = new Random();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS", new Locale("ko","KO"));
		String formattedValue = formatter.format(new Date());
		String id = formattedValue + random.nextInt(10);
		return id;
	}
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	@RequestMapping(value="/comm/download.do")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, RuntimeException{

		//파일 저장경로
		String path = request.getSession().getServletContext().getRealPath("");
 	   	path = new java.io.File(new java.io.File(path).getParent()) + System.getProperty("file.separator") + "upload_data\\";
		
    	String downFolder = request.getParameter("folder");
    	String orgFileName = request.getParameter("fname");
    	File file = new File(path + downFolder + System.getProperty("file.separator") + orgFileName);

    	if (!file.exists() || !file.isFile()) {
    		response.setContentType("text/html;charset=UTF-8");
    		java.io.PrintWriter out = response.getWriter();
    		out.println("<script>alert(\"파일이 없습니다.\");history.back();</script>");
    		return;
    	}

    	int fSize = (int)file.length();
    	if (fSize > 0) {
    	    BufferedInputStream in = null;
    	    FileInputStream  fi = null;

    	    try {
    	    	fi = new FileInputStream(file);
    	    	in = new BufferedInputStream(fi);

	    		response.setContentType("application/unknown");
	    		response.setHeader("Content-Disposition", "attachment; filename=" + new String(orgFileName.getBytes("utf-8"),"8859_1"));
	    		response.setContentLength(fSize);
	    		FileCopyUtils.copy(in, response.getOutputStream());
    	    } finally {
	    		if (in != null) {
	    		    try {
	    		    	in.close();
	    		    } catch (IOException ignore) {
	    		    	logger.error("예외 상황 발생");
	    		    }
	    		}
		    	fi.close();
    	    }
    	    response.getOutputStream().flush();
    	    response.getOutputStream().close();
    	}
    	
    }
	
	
}
