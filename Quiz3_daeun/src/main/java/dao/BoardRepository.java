package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import dto.Board;

public class BoardRepository  {
	private static BoardRepository instance;
	private DBConnection conn;
	
	private BoardRepository() {
		conn = new DBConnection();
	}
	
	public static BoardRepository getInstance() {
		if(instance==null) instance= new BoardRepository();
		return instance;
	}
	
	//페이지처리를 위한 게시물 갯수 세기
	public int getListCount() throws ClassNotFoundException, SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		String sql="select count(num) as cnt from board";
		stmt=conn.getConnection().createStatement();
		rs=stmt.executeQuery(sql);
		int cnt=0;
		if (rs.next()) {
		    cnt = rs.getInt("cnt"); 
		}
		return cnt;
	}
	
	//게시판 리스트 페이지처리해서 가져오기
	public List<Board> getList(int page) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<Board> boardList = new ArrayList<>();
		int first = (page-1)*10;
		try {
			String sql = "select num, id, subject, regist_day	from board order by num desc limit ?,9 ";
			pstmt = conn.getConnection().prepareStatement(sql);
			pstmt.setInt(1 ,first);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getString("id"));
				board.setNum(rs.getInt("num"));
				board.setSubject(rs.getString("subject"));
				board.setRegist_day(rs.getString("regist_day"));
				boardList.add(board);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("sql 에러");
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
		return boardList;
	}
	
	//검색한 글만 가져오기
	public List<Board> getSearchList(int page, String subject) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<Board> boardList = new ArrayList<>();
		int first = (page-1)*10;
		try {
			String sql = "select num, id, subject, regist_day from board where subject like ? order by num desc limit ?,9 ";
			pstmt = conn.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%"+subject+"%");
			pstmt.setInt(2 ,first);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getString("id"));
				board.setNum(rs.getInt("num"));
				board.setSubject(rs.getString("subject"));
				board.setRegist_day(rs.getString("regist_day"));
				boardList.add(board);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("sql 에러");
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
		return boardList;
	}
	
	
	public boolean registerBoard(Board board) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			String sql="insert into board (id, subject, content, regist_day,ip) values (?,?,?,?,?)";
			pstmt= conn.getConnection().prepareStatement(sql);
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getRegist_day());
			pstmt.setString(5, board.getIp());
			pstmt.executeUpdate();
			
			System.out.println("등록성공");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("등록실패");
			return false;
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
	}
	
	public Board detailBoard(int num) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Board board = new Board();
		try {
			String sql = "select id, subject, content from board where num=?";
			pstmt = conn.getConnection().prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
				
			if (rs.next()) {
			    board.setId(rs.getString("id"));
			    board.setSubject(rs.getString("subject"));
			    board.setContent(rs.getString("content"));

			}
	
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("sql 에러");
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
		return board;
	}
	
	public boolean updateBoard(Board board) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			String sql="update board SET subject =?, content =?, regist_day=? WHERE num = ?;";
			pstmt= conn.getConnection().prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getRegist_day());
			pstmt.setInt(4, board.getNum());
			pstmt.executeUpdate();
			
			System.out.println("수정 성공");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("수정 실패");
			return false;
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
	}
	
	public boolean deleteBoard(int num) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			String sql="delete from board where num=?;";
			pstmt= conn.getConnection().prepareStatement(sql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();
			
			System.out.println("삭제 성공");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
			return false;
		}finally {
			if(pstmt!=null) pstmt.close();;
			if(rs!=null) rs.close();
		}
	}

}
