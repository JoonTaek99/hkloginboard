package com.hk.calboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.calboard.command.DeleteCalCommand;
import com.hk.calboard.command.InsertCalCommand;
import com.hk.calboard.dtos.CalDto;
import com.hk.calboard.service.ICalService;
import com.hk.calboard.utils.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/schedule")
public class CalController {
 
	//log를 원하는 위치에 설정하여 디버깅하기
	private static final Logger logger = LoggerFactory.getLogger(CalController.class);
	
	@Autowired
	private ICalService calService;
	
	@GetMapping(value="/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		logger.info("달력보기");
		Map<String, Integer>map=calService.makeCalendar(request);
		model.addAttribute("calMap", map);
		return "thymeleaf/calboard/calendar";
	}
	
	@GetMapping(value = "/addCalBoardForm")
	public String addCalBoardForm(Model model, InsertCalCommand insertCalCommand) {
		logger.info("일정추가폼 이동");
		System.out.println(insertCalCommand);
		//addCalBoardForm 페이지에서 유효값 처리를 위해 insertCalCommand 받고있기떄문에
		model.addAttribute("insertCalCommand", insertCalCommand);
		return "thymeleaf/calboard/addCalBoardForm";
	}
	
	@PostMapping(value = "/addCalBoard")
	public String addCalBoard(@Validated InsertCalCommand insertCalCommand,
											BindingResult result) {
		logger.info("일정추가하기");
		System.out.println(insertCalCommand);
		if(result.hasErrors()) {
			System.out.println("글모두입력하라고장난하나진짜한두번써보는것도아니고제대로좀써줘뭐하는거야진짜");
			return "thymeleaf/calboard/addCalBoardForm";
		}
		calService.insertCalBoard(insertCalCommand);
		return "redirect:/schedule/calendar";
	}
	
	@GetMapping(value = "/calBoardList")
	public String calBoardList(@RequestParam Map<String, String> map
			, HttpServletRequest request
			, Model model) {
		logger.info("일정목록보기");
//		HttpSession session = request.getSession();
		String id = "ljt"; //임시로 id 저장
		
		//command 유효값 처리를 위해 기본 생성해서 보내줌
		model.addAttribute("deleteCalCommand", new DeleteCalCommand());
		
		//일정목록을 조회할떄마다 year, month, date를 세션에 저장
		HttpSession session = request.getSession();
		
		if(map.get("year")==null) {
			//조회한 상태이기 때문에 ymd가 저장되어 있어서 값을 가져옴
			map = (Map<String, String>)session.getAttribute("ymdMap");
		}else {
			//일정을 처음 조회했을 때 ymd를 저장함
			session.setAttribute("ymdMap", map);
		}
		
		//달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
		String yyyyMMdd = map.get("year")+Util.isTwo(map.get("month"))
									+Util.isTwo(map.get("date"));
		
		List<CalDto> list = calService.calBoardList(id, yyyyMMdd);
		model.addAttribute("list", list);
		
		return "thymeleaf/calboard/calBoardList";
	}
	
	@PostMapping(value = "/calMulDel")
	public String calMulDel(@Validated DeleteCalCommand deleteCalCommand,
							BindingResult result,
							HttpServletRequest request,
							Model model) {
		
		if(result.hasErrors()) {
			System.out.println("최소 하나 이상 체크하기");
			
			HttpSession session=request.getSession();
//			String id=session.getAttribute("id");
			String id="ljt";//임시로 id 저장
			
			//session에 저장된 ymd 값은 목록 조회할때 추가되는 코드임
			Map<String, String>map=(Map<String, String>)session.getAttribute("ymdMap");
			
			//달력에서 전달받은 파라미터 year, month, date를 8자리로 만든다.
			String yyyyMMdd=map.get("year")
					       +Util.isTwo(map.get("month"))
					       +Util.isTwo(map.get("date"));
			List<CalDto> list= calService.calBoardList(id, yyyyMMdd);
			model.addAttribute("list", list);
			return "thymeleaf/calboard/calBoardList";
		}
		Map<String,String[]>map=new HashMap<>();
		map.put("seqs", deleteCalCommand.getSeq());
		calService.calMulDel(map);
		
		return "redirect:/schedule/calBoardList";
	}
	
}



