package com.hk.board.controller;

import java.io.IOException;
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
import com.hk.board.dtos.BoardDto;
import com.hk.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import javassist.expr.NewArray;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
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
}
