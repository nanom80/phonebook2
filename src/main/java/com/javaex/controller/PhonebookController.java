package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDAO;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVO;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
	//생성자
	public PhonebookController() {
		super();
	}
	
	//메소드gs
	
	//메소드일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("◎PhonebookController");
		
		String action = request.getParameter("action");
		
		System.out.println("◎action="+action);
		
		if("list".equals(action)) {
			System.out.println("★리스트");
			
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			List<PersonVO> personList =  phonebookDAO.personSelect();
			
			//list.jsp에게 후반일 html을 만들고 응답문서 만들어 보낸다
			//1)request객체에 데이타를 넣는다
			request.setAttribute("pList", personList);
			
			//2)list.jsp에 request 객체와 response 객체를 보낸다(*포워드)
			//RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			//rd.forward(request, response);
			WebUtil.forward(request, response, "/list.jsp"); //Dispatcher를 대체
		
		}else if("wform".equals(action)) {
			System.out.println("★등록폼");
			
			//등록폼을 응답해야한다
			//db관련 할 일이 없다
			
			//jsp에게 화면을 그리게 한다(포워드)
			//writeForm.jsp 포워드
			//RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			//rd.forward(request, response);
			WebUtil.forward(request, response, "/writeForm.jsp"); //Dispatcher를 대체
			
		}else if("write".equals(action)) {
			System.out.println("★등록");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//데이터를 묶는다
			PersonVO personVO = new PersonVO(name, hp, company);
			System.out.println(personVO);
			
			//DAO를 통해서 저장시키기
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personInsert(personVO);
			
			//리다이렉트
			System.out.println("★리다이렉트");
			response.sendRedirect("http://192.168.0.99:8080/phonebook2/pbc?action=list");
			
			/*
			//응답(리스트)
			List<PersonVO> personList = phonebookDAO.personSelect();
			
			//request의 어트리뷰트 영역에 데이타 넣기
			//1)request객체에 데이타를 넣는다
			request.setAttribute("pList", personList);
			
			//2)list.jsp에 request 객체와 response 객체를 보낸다(*포워드)
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
			
		}else if("delete".equals(action)) {
			System.out.println("★삭제");
			
			int pId = Integer.parseInt(request.getParameter("pId"));
			
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personDelete(pId);
			
			//리다이렉트
			System.out.println("★리다이렉트");
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("mform".equals(action)) {
			System.out.println("★수정폼");
			
			//파라미터에서  no 꺼내온다
			int no =  Integer.parseInt(request.getParameter("no"));
			
			//dao를 통해서 no를 주고 삭제
			PhonebookDAO phonebookDAO= new PhonebookDAO();
			PersonVO personVO= phonebookDAO.personSelectOne(no);
			
			//request객체에 데이터를 넣어준다
			request.setAttribute("pVO", personVO);
			
			//포워드
			//RequestDispatcher rd = request.getRequestDispatcher("/modifyForm.jsp");
			//rd.forward(request, response);
			WebUtil.forward(request, response, "/modify.jsp");
		
		}else if("modify".equals(action)) {
			System.out.println("★수정");
			
			//파라미터 4개의 정보를 꺼내온다
			int no =  Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp =  request.getParameter("hp");
			String company = request.getParameter("company");
			
			//데이터를 묶는다
			PersonVO personVO = new PersonVO(no, name, hp, company);
			
			//dao를 통해서 no를 주고 삭제
			PhonebookDAO phonebookDAO= new PhonebookDAO();
			phonebookDAO.personUpdate(personVO);
			
			//리다이렉트 action=list
			response.sendRedirect("http://192.168.0.99:8080/phonebook2/pbc?action=list");
			
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
