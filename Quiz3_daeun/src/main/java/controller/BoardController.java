package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import dao.BoardRepository;
import dto.Board;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uri = req.getRequestURI();
		String ctxPath= req.getContextPath();
		String cmd= uri.substring(ctxPath.length());
		System.out.println("cmd="+ cmd);
		
		res.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		//게시판 리스트 가져오기
		if(cmd.equals("/boards/list")) {
			try {
				List<Board> boardList = requestBoardList(req);
				int count = getBoardCnt();
				 System.out.println("컨트롤러에서 가져온 게시글 수: " + boardList.size());
				 req.setAttribute("boardList", boardList);
				 req.setAttribute("cnt", count);
			}catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "boardC 에서 오류");
				System.out.println("boardC 에서 오류");	
			}
			RequestDispatcher rd = req.getRequestDispatcher("/board/list.jsp");
			rd.forward(req, res);
		}
		//검색한 리스트 가져오기
		if(cmd.equals("/boards/search")) {
			try {	
				String keyword = req.getParameter("search");
				List<Board> boardList = searchBoard(req, keyword);
				int count = boardList.size();
				System.out.println("컨트롤러에서 가져온 게시글 수: " + boardList.size());
				 req.setAttribute("boardList", boardList);
				 req.setAttribute("cnt", count);
			}catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "search 에서 오류");
				System.out.println("search 에서 오류");	
			}
			RequestDispatcher rd = req.getRequestDispatcher("/board/list.jsp");
			rd.forward(req, res);
		}
		//등록페이지 가져오기
		if(cmd.equals("/boards/register")) {
			RequestDispatcher rd= req.getRequestDispatcher("/board/register.jsp");
			rd.forward(req, res);
		}
		
		//쓴 글 상세보기
		if(cmd.equals("/boards/detail")) {
			try {
				int num = Integer.parseInt(req.getParameter("num"));
				System.out.println("num=" + num);
				Board board = detailBoard(num);
				board.setNum(num);
				req.setAttribute("boardDetail", board);
			}catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "boardc오류");
				
			}
			RequestDispatcher rd= req.getRequestDispatcher("/board/detail.jsp");
			rd.forward(req, res);			
		}
		
		//글 삭제하기
		if(cmd.equals("/boards/deleteBoard")) {
			try {
				int num= Integer.parseInt(req.getParameter("num"));
				System.out.println("num=" + num);
				deleteBoard(num);
				
			}catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "deleteBoard오류");
				
			}
			RequestDispatcher rd= req.getRequestDispatcher("/boards/list");
			rd.forward(req, res);			
		}
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uri = req.getRequestURI();
		String ctxPath= req.getContextPath();
		String cmd= uri.substring(ctxPath.length());
		System.out.println("cmd="+ cmd);
		
		//글등록
		if(cmd.equals("/boards/register")) {
			try {
				Board board= new Board();
				board.setId((String) req.getParameter("id"));
				board.setSubject((String) req.getParameter("subject"));
				board.setContent((String) req.getParameter("content"));
				board.setIp((String) req.getRemoteAddr());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm");
				String formatted = now.format(formatter);
				board.setRegist_day(now	.format(formatter));
				
				registerBoard(board);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("register post에서 오류");
			}
			res.sendRedirect(req.getContextPath()+"/boards/list");	
		}
	
	
	//수정페이지 이동
		if(cmd.equals("/boards/boardDetailUpdate")) {
			try {
				int num = Integer.parseInt(req.getParameter("num"));
				System.out.println("num=" + num);
				Board board = detailBoard(num);
				board.setNum(num);
				req.setAttribute("boardDetail", board);
			}catch(Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "boardDetailUpdate오류");
				
			}
			RequestDispatcher rd= req.getRequestDispatcher("/board/updateDetail.jsp");
			rd.forward(req, res);			
		}	
		
		//수정
		if(cmd.equals("/boards/detailUpch")) {
			try {
					int num = Integer.parseInt(req.getParameter("num"));
					System.out.println("num=" + num);
					Board board = detailBoard(num);
					board.setNum(num);
					req.setAttribute("boardDetail", board);
					board.setNum(Integer.parseInt( req.getParameter("num")));
					board.setSubject((String) req.getParameter("subject"));
					board.setContent((String) req.getParameter("content"));
					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm");
					String formatted = now.format(formatter);
					board.setRegist_day(now	.format(formatter));
					
					updateBoard(board);
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("update post에서 오류");
				}
			RequestDispatcher rd= req.getRequestDispatcher("/board/detail.jsp");
			rd.forward(req, res);		
		}
	}
	
	
	

	public List<Board> requestBoardList(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		System.out.print(req.getParameter("page"));
		int page =0;
		if(req.getParameter("page")==null){
			page = 1;
		}else {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		return BoardRepository.getInstance().getList(page);	
	}
	
	public List<Board> searchBoard(HttpServletRequest req, String keyword) throws ClassNotFoundException, SQLException{
		System.out.print(req.getParameter("page"));
		int page =0;
		System.out.println("sub= "+keyword);
		if(req.getParameter("page")==null){
			page = 1;
		}else {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		return BoardRepository.getInstance().getSearchList(page, keyword);	
	}
	
	public int getBoardCnt() throws ClassNotFoundException, SQLException {
		return BoardRepository.getInstance().getListCount();
	}
	
	public boolean registerBoard(Board board) throws SQLException {
		return BoardRepository.getInstance().registerBoard(board);
	}
	
	public Board detailBoard(int num) throws ClassNotFoundException, SQLException {
		return BoardRepository.getInstance().detailBoard(num);
		
	}
	
	private boolean updateBoard(Board board) throws ClassNotFoundException, SQLException {
		return BoardRepository.getInstance().updateBoard(board);
	}
	private boolean deleteBoard(int num) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return BoardRepository.getInstance().deleteBoard(num);
	}
}
