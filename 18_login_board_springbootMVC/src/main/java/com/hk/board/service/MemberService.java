package com.hk.board.service;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hk.board.command.AddUserCommand;
import com.hk.board.command.LoginCommand;
import com.hk.board.dtos.MemberDto;
import com.hk.board.mapper.MemberMapper;
import com.hk.board.status.RoleStatus;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor ;lombok 기능: final 필드를 초기화 - Autowired 생략가능
@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean addUser(AddUserCommand addUserCommand) {
		MemberDto mdto=new MemberDto();
		mdto.setId(addUserCommand.getId());
		mdto.setName(addUserCommand.getName());
		
		//password암호화하여 저장하자
		mdto.setPassword(passwordEncoder.encode(addUserCommand.getPassword()));
		
		mdto.setEmail(addUserCommand.getEmail());
		mdto.setAddress(addUserCommand.getAddress());
		mdto.setRole(RoleStatus.USER+"");
		
		return memberMapper.addUser(mdto);
	}
	
	public String idChk(String id) {
		return memberMapper.idChk(id);
	}
	
	public String login(LoginCommand loginCommand, HttpServletRequest request, Model model) {
		MemberDto dto = memberMapper.loginUser(loginCommand.getId());
		String path = "home";
		if(dto!=null) {
			// 로그인 폼에서 입력받은 패스워드 값과 DB에 암호화된 패스워드 비교
			if(passwordEncoder.matches(loginCommand.getPassword(), dto.getPassword())) {
				System.out.println("패스워드 같음: 회원맞네");
				//session객체에 로그인 정보 저장
				request.getSession().setAttribute("mdto", dto);
				return path;
			}else {
				System.out.println("패스워드 틀림ㅋ");
				model.addAttribute("msg", "패스워드 확인좀 해라 이것도 헷갈리면 다른건 어떡할라고 그러냐");
				path="member/login";
			}
		}else {
			System.out.println("회원아닌데 너 누구냐 수상한데");
			model.addAttribute("msg", "아이디좀확인해라 계속 이럴래 한두번해야지 진짜선넘네");
			path="member/login";
		}
		return path;
	}
	
}





