package com.hk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.FileBoardDto;

@Mapper
public interface FileMapper {

	public boolean insertFileBoard(FileBoardDto dto);
	
	public FileBoardDto getFileInfo(int file_seq);
	
}
