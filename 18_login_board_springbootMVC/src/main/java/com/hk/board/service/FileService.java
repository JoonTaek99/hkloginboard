package com.hk.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.dtos.FileBoardDto;
import com.hk.board.mapper.FileMapper;

@Service
public class FileService {
	
	@Autowired
	private FileMapper fileMapper;
	
	// 파일 업로드 하기
	public List<FileBoardDto> uploadFiles(String uploadPath, MultipartRequest multipartRequest) throws IllegalStateException, IOException{
		
		// 여러개의 파일들을 List에 담는 코드
		List<MultipartFile> multipartFiles = multipartRequest.getFiles("filename");
		
		// 업로드된 파일들의 정보(원본명, 저장명)를 담아줄 LIST
		List<FileBoardDto> uploadFileList = new ArrayList<>();
		
		for(MultipartFile multipartFile:multipartFiles) {
			// 원본 파일명 구하기
			String origin_filename = multipartFile.getOriginalFilename();
			
			// 저장 파일명 구하기
			String stored_filename = UUID.randomUUID() + origin_filename.substring(origin_filename.indexOf("."));
			
			// 파일 저장 경로 구하기
			String fileuploadUrl = uploadPath + "/" + stored_filename;
			multipartFile.transferTo(new File(fileuploadUrl));

			// 각각의 파일정보를 list에 저장하는 코드
			uploadFileList.add(new FileBoardDto(0,0,origin_filename,stored_filename));
			
		}
		
		return uploadFileList;
	}
	
}














