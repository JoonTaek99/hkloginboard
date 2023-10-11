package com.hk.user.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.user.dtos.HkDto;

public class HkDao extends DataBase{

	public HkDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	//글목록 조회 가능 : 반환값 List<HKDto> --> 여러개의 행일 경우
			public List<HkDto> getAllList(String id){
				List<HkDto> list = new ArrayList<>();
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				String sql=" SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE, DELFLAG  "
						+ " FROM T_BOARD   "
						+ " WHERE TID=? ORDER BY TSEQ";
				
				try {
					conn = getConnection(); //2단계 : DB연결하기
					psmt=conn.prepareStatement(sql); //3단계:쿼리준비하기
					psmt.setString(1, id);
					rs = psmt.executeQuery();//4단계:쿼리실행하기
					while(rs.next()) { //rs객체안에 데이터가 있는지 여부 확인
						HkDto dto = new HkDto();
						dto.setTseq(rs.getInt(1));
						dto.setTid(rs.getString(2));
						dto.setTtitle(rs.getString(3));
						dto.setTcontent(rs.getString(4));
						dto.setTregdate(rs.getDate(5));
						dto.setDelflag(rs.getString(6));
						list.add(dto);
						System.out.println(dto);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("JDBC실패:"+getClass()+":"+"getAllList()");
					e.printStackTrace();
				}finally {
					close(rs, psmt, conn);//6단계 : 쿼리결과닫기
				}
						
				return list;
			}
			
			public List<HkDto> getAllList(){
				List<HkDto> list = new ArrayList<>();
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				String sql=" SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE, DELFLAG  "
						+ " FROM T_BOARD ORDER BY TSEQ";
				
				try {
					conn = getConnection(); //2단계 : DB연결하기
					psmt=conn.prepareStatement(sql); //3단계:쿼리준비하기
					rs = psmt.executeQuery();//4단계:쿼리실행하기
					while(rs.next()) { //rs객체안에 데이터가 있는지 여부 확인
						HkDto dto = new HkDto();
						dto.setTseq(rs.getInt(1));
						dto.setTid(rs.getString(2));
						dto.setTtitle(rs.getString(3));
						dto.setTcontent(rs.getString(4));
						dto.setTregdate(rs.getDate(5));
						dto.setDelflag(rs.getString(6));
						list.add(dto);
						System.out.println(dto);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("JDBC실패:"+getClass()+":"+"getAllList()");
					e.printStackTrace();
				}finally {
					close(rs, psmt, conn);//6단계 : 쿼리결과닫기
				}
						
				return list;
			}
			
			//글추가 :insert문 실행, 파라미터(id,title,content), seq, regdate -> 쿼리에서 추가
			//테이블 수정 --> 결과값이 없음
			public boolean insertBoard(HkDto dto) {
				int count=0;
				Connection conn = null;
				PreparedStatement psmt = null;
				
				String sql = " INSERT INTO T_BOARD "
							+ " VALUES(?,?,?,?,SYSDATE(),'N' ) ";
						
				try {
					conn = getConnection(); //2단계:DB연결
					psmt = conn.prepareStatement(sql); //3단계:쿼리준비
					psmt.setInt(1, dto.getTseq());
					psmt.setString(2, dto.getTid());
					psmt.setString(3, dto.getTtitle());
					psmt.setString(4, dto.getTcontent()); //3단계 완료
					
					//4단계:쿼리실행
					count = psmt.executeUpdate(); //테이블을 수정하기 떄문에 executeUpdate()
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(null, psmt, conn);
				}
				
				return count>0?true:false;
			}
			//글상세조회: select문 실행, 파라미터(pk: seq)
			//조회기능이니까 결과가 있음 -> ResultSet 필요
			//반환타입 : 
			public HkDto getBoard(int seq) {
				HkDto dto = new HkDto();
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				String sql=" SELECT TSEQ, TID, TTITLE, TCONTENT, TREGDATE "
						+ " FROM T_BOARD "
						+ " WHERE TSEQ = ? ";
				
				try {
					conn = getConnection();
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, seq);
					rs = psmt.executeQuery(); //조회 --> executeQuery() 사용
					while(rs.next()) {
						dto.setTseq(rs.getInt(1)); //setSeq(int타입)
						dto.setTid(rs.getString(2)); //setId(String타입)
						dto.setTtitle(rs.getString(3));
						dto.setTcontent(rs.getString(4));
						dto.setTregdate(rs.getDate(5)); //setRegDate(Date타입)
						System.out.println(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(rs, psmt, conn);
				}
				
				return dto;
			}
			
			//글수정하기 : update 문 실행, 파라미터(seq, title, content), regdate는 쿼리에서 수정
			//결과 X -> 테이블을 수정
			public boolean updateBoard(HkDto dto) {
				int count =0;
				Connection conn = null;
				PreparedStatement psmt = null;
				
				String sql = " UPDATE t_board "
						+ " SET TTITLE=?, TCONTENT=?,TREGDATE=SYSDATE() "
						+ " WHERE TSEQ=? ";
				try {
					conn=getConnection();
					psmt=conn.prepareStatement(sql);
					psmt.setString(1, dto.getTtitle());
					psmt.setString(2, dto.getTcontent());
					psmt.setInt(3, dto.getTseq());
					
					count = psmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(null, psmt, conn);
				}
				
				
				return count>0?true:false;
			}
			
			//글삭제
			   public boolean deleteBoard(int seq) {
				      int count = 0;
				      
				      Connection conn=null;
				      PreparedStatement psmt=null;
				      ResultSet rs= null;
				      String sql = " UPDATE t_board "
								+ " SET TTITLE='--삭제된글입니다--', DELFLAG='Y' "
								+ " WHERE TSEQ=? ";
				      
				      try {
				         conn=getConnection();//db연결
				         psmt=conn.prepareStatement(sql);//쿼리준비
				         psmt.setInt(1, seq);
				         count=psmt.executeUpdate();//테이블을 수정하기 때문에 executeUpdate()
				      } catch (SQLException e) {
				         // TODO Auto-generated catch block
				         e.printStackTrace();
				      }finally {
				         close(null, psmt, conn);
				      }
				      
				      return count>0?true:false;
				}
			   
			   public boolean mulDel(String[] seqs) {
				      boolean isS=true;//성공여부
				      int [] count=null; //쿼리실행결과 개수
				      
				      Connection conn=null;
				      PreparedStatement psmt=null;
				      
				      String sql = " UPDATE t_board "
								+ " SET TTITLE='--삭제된글입니다--', DELFLAG='Y' "
								+ " WHERE TSEQ=? ";
				      
				      try {
				         conn=getConnection();
				         //자동 커밋 - 수동 설정
				         conn.setAutoCommit(false);
				         psmt=conn.prepareStatement(sql);
				         
				         for (int i = 0; i < seqs.length; i++) {
				            psmt.setString(1, seqs[i]);//seq의 타입이 String임
				            psmt.addBatch();//완성된 쿼리가 배치됨 delete문 하나 저장
				            
				         }
				         count=psmt.executeBatch();
				         conn.commit();
				      } catch (SQLException e) {
				         // TODO Auto-generated catch block
				         try {
				            conn.rollback();
				         } catch (SQLException e1) {
				            // TODO Auto-generated catch block
				            e1.printStackTrace();
				         }
				         e.printStackTrace();
				      }finally {
				         try {
				            conn.setAutoCommit(true);
				            
				         } catch (SQLException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				         }
				         close(null, psmt, conn);
				         
				         for (int i = 0; i < count.length; i++) {
				            if(count[i]!=1) {
				               isS=false;
				               break;
				               
				            }
				            
				         }
				      }
				      
				      return isS;
				   }
	   		
	
	
	
}






















