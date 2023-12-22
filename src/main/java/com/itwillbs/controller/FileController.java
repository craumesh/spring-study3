package com.itwillbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	// http://localhost:8088/fileUpload
	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public void fileUploadFormGET() throws Exception {
		logger.debug("fileUploadFormGET() 실행");
		logger.debug("fileUpload.jsp 뷰페이지 연결");
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUploadFormPOST(MultipartHttpServletRequest multiRequest, Model model) throws Exception {
		logger.debug("fileUploadFormPOST() 실행");
		logger.debug("파일 업로드 처리");
		
		// 파일업로드시 전달된 모든정보를 저장
		Map paramMap = new HashMap();
		
		// 파일정보를 제외한 모든 파라미터의 이름을 가져오기
		Enumeration enu =  multiRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multiRequest.getParameter(name);
			logger.debug("name : "+name+", value : "+value);
			paramMap.put(name, value);
		}
		logger.debug("paramMap : "+paramMap);
		
		List fileList = fileProcess(multiRequest);
		paramMap.put("fileList", fileList);
		model.addAttribute("paramMap",paramMap);
		
		return "fileResult";
	}
	
	// 전달받은 파일정보(이름)을 저장, 파일업로드 처리
	private List<String> fileProcess(MultipartHttpServletRequest multiRequest) throws Exception{
		
		// 파일의 이름을 저장
		List<String> fileList = new ArrayList<String>();
		
		// 폼태그에서 전달된 파일의 정보를 받아오기
		Iterator<String> fileNames = multiRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			
			// 전달된 파일이름에 해당하는 MultipartFile정보 저장
			MultipartFile mFile = multiRequest.getFile(fileName);
			
			String oFileName = mFile.getOriginalFilename();
			logger.debug("oFileName : "+oFileName);
			// 업로드된 실제 파일의 이름을 저장
			fileList.add(oFileName);
			
			File file = new File("D:\\springupload\\"+oFileName);
			if(mFile.getSize() != 0) { // 첨부파일이 존재할때
				if(!file.exists()) { // 파일,디렉터리(폴더)가 존재하는지 체크
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(file);
			}			
		}		
		return fileList;
	}
	
	// http://localhost:8088/fileUpload
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void fileDownloadGET(String fileName, HttpServletResponse response) throws Exception {
		logger.debug("fileDownloadGET() 실행");
		logger.debug("fileName : "+fileName);
		
		// response를 통하는 통로 생성
		OutputStream out = response.getOutputStream();
		
		// 다운로드할 파일의 정보
		String downFile = "D:\\springupload\\"+fileName;
		
		// 다운로드할 파일객체 생성
		File file = new File(downFile);
		
		// 한글 파일명의 경우 인코딩 처리
		fileName = URLEncoder.encode(fileName,"UTF-8");
		
		// 썸네일 처리 동작 ------------------------------------------------------------------------
		int lastIdx = fileName.lastIndexOf("."); // 확장자 이전까지의 인덱스
		String tmpFileName = fileName.substring(0,lastIdx);
		
		File thumbFile = new File("D:\\springupload\\thumb\\"+tmpFileName+".png");
		
		if(thumbFile.exists()) {
			thumbFile.getParentFile().mkdirs();
			Thumbnails.of(file).size(50,50).outputFormat("png").toFile(thumbFile);
		}
		// 썸네일 처리 동작 ------------------------------------------------------------------------
		
		// 다운로드 창의 형태로 다운로드 되로록 실행
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buffer = new byte[1024*8];
		int data = 0;
		while((data = fis.read(buffer)) != -1) {
			out.write(buffer,0,data);
		}
		out.flush(); // 공백 채워서 전달
		
		fis.close();
		out.close();
	}
}
