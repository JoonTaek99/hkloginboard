package com.hk.user.dtos;

import java.io.Serializable;
import java.sql.Date;

//DTO객체 : 데이터를 저장하고 운반하기 위한 객체
//데이터를 담는 용도이기 때문에 멤버필드는 은닉화로 관리함.
public class HkDto implements Serializable {
//Serializable 인터페이스 구현 -> 직렬화 data [d a t a] 하여 전송하는 방법
//데이터를 안정적으로 관리
		private static final long serialVersionUID = 1L;
		
		private String tid;
		private int tseq;
	    private String ttitle;
	    private String tcontent;
	    private Date tregdate;
	    private String delflag;
	    
	    public HkDto() {
	    	super();
			// TODO Auto-generated constructor stub
		}
		public String getTid() {
			return tid;
		}
		public void setTid(String tid) {
			this.tid = tid;
		}
		public int getTseq() {
			return tseq;
		}
		public void setTseq(int tseq) {
			this.tseq = tseq;
		}
		public String getTtitle() {
			return ttitle;
		}
		public void setTtitle(String ttitle) {
			this.ttitle = ttitle;
		}
		public String getTcontent() {
			return tcontent;
		}
		public void setTcontent(String tcontent) {
			this.tcontent = tcontent;
		}
		public Date getTregdate() {
			return tregdate;
		}
		public void setTregdate(Date tregdate) {
			this.tregdate = tregdate;
		}
		public String getDelflag() {
			return delflag;
		}
		public void setDelflag(String delflag) {
			this.delflag = delflag;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		public HkDto(String tid, String ttitle, String tcontent) {
			super();
			this.tid = tid;
			this.ttitle = ttitle;
			this.tcontent = tcontent;
		}
		
		
		public HkDto(int tseq, String ttitle, String tcontent) {
			super();
			this.tseq = tseq;
			this.ttitle = ttitle;
			this.tcontent = tcontent;
		}
		@Override
		public String toString() {
			return "HkDto [tid=" + tid + ", tseq=" + tseq + ", ttitle=" + ttitle + ", tcontent=" + tcontent
					+ ", tregdate=" + tregdate + ", delflag=" + delflag + "]";
		}
		
		
		
}
