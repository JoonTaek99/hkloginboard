package com.hk.user.dtos;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{

      //DTO 만들기 내용 및 순서
      //맴버필드작성
      //default생성자
      //생성자 오버로딩
      //getter,setter 메서드 작성
      //toString() 오버로딩
      
      private static final long serialVersionUID = 1L;

      private String tid;
      private String tpassword;
      private String tname;
      private String taddress;
      private String tphone;
      private String temail;
      private String tenabled;
      private String trole;
      private Date regdate;
      
      
      public UserDto() {
    	  super();
		// TODO Auto-generated constructor stub
	}
      
	

	
	public String getTid() {
		return tid;
	}




	public void setTid(String tid) {
		this.tid = tid;
	}




	public String getTpassword() {
		return tpassword;
	}




	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}




	public String getTname() {
		return tname;
	}




	public void setTname(String tname) {
		this.tname = tname;
	}




	public String getTaddress() {
		return taddress;
	}




	public void setTaddress(String taddress) {
		this.taddress = taddress;
	}




	public String getTphone() {
		return tphone;
	}




	public void setTphone(String tphone) {
		this.tphone = tphone;
	}




	public String getTemail() {
		return temail;
	}




	public void setTemail(String temail) {
		this.temail = temail;
	}




	public String getTenabled() {
		return tenabled;
	}




	public void setTenabled(String tenabled) {
		this.tenabled = tenabled;
	}




	public String getTrole() {
		return trole;
	}




	public void setTrole(String trole) {
		this.trole = trole;
	}




	public Date getRegdate() {
		return regdate;
	}




	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "UserDto [tid=" + tid + ", tpassword=" + tpassword + ", tname=" + tname + ", taddress=" + taddress
				+ ", tphone=" + tphone + ", temail=" + temail + ", tenabled=" + tenabled + ", trole=" + trole
				+ ", regdate=" + regdate + "]";
	}




	public UserDto(String tid, String taddress, String temail) {
		super();
		this.tid = tid;
		this.taddress = taddress;
		this.temail = temail;
	}

	public UserDto(String tid, String tpassword, String tname, String taddress, String tphone, String temail) {
		super();
		this.tid = tid;
		this.tpassword = tpassword;
		this.tname = tname;
		this.taddress = taddress;
		this.tphone = tphone;
		this.temail = temail;
	}

	

	
    
	
}





















