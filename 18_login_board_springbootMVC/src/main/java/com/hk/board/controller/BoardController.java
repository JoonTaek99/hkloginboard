package com.hk.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.command.DelBoardCommand;
import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.dtos.FileBoardDto;
import com.hk.board.mapper.BoardMapper;
import com.hk.board.service.BoardService;
import com.hk.board.service.FileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javassist.expr.NewArray;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping(value = "/boardList")
	public String boardList(Model model) {
		System.out.println("글목록 보기");
		List<BoardDto> list = boardService.getAllList();
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		
		return "board/boardList";
	}
	
	@GetMapping(value = "/boardInsert")
	public String boardInsertForm(Model model) {
		System.out.println("글추가폼 이동");
		model.addAttribute("insertBoardCommand", new InsertBoardCommand());
		
		return "board/boardInsertForm";
	}
	
	@PostMapping(value = "/boardInsert")
	public String boardInsert(@Validated InsertBoardCommand insertBoardCommand,
									BindingResult result, MultipartRequest multipartRequest,
									HttpServletRequest request,
									Model model) throws IllegalStateException, IOException {
		
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력하라고");
			return "board/boardInsertForm";
		}
		
		boardService.insertBoard(insertBoardCommand, multipartRequest, request);
		
		return "redirect:/board/boardList";
	}
	
	@GetMapping(value = "/boardDetail")
	public String boardDetail(int board_seq, Model model) {
		BoardDto dto = boardService.getBoard(board_seq);
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		model.addAttribute("dto",dto);
		return "board/boardDetail";
	}
	
	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(@Validated UpdateBoardCommand updateBoardCommand
										,BindingResult result) {
		System.out.println("수정시작");
		if(result.hasErrors()) {
			System.out.println("수정내용을 모두 입력하셈");
			return "board/boardDetail";
		}
		
		boardService.updateBoard(updateBoardCommand);
		
		return "redirect:/board/boardDetail?board_seq="+updateBoardCommand.getBoard_seq();
		
	}
	
	@GetMapping(value = "/download")
	public void download(int file_seq, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		FileBoardDto fdto = fileService.getFileInfo(file_seq);
		
		fileService.fileDownload(fdto.getOrigin_filename(),fdto.getStored_filename(),request,response);
	}
}

















