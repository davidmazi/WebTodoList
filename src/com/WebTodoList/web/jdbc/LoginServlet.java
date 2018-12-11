package com.WebTodoList.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDBUtil accountDBUtil;

	@Resource(name = "jdbc/webtodolist")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		accountDBUtil = new AccountDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						request.setAttribute("username", cookie.getValue());
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String u = request.getParameter("user");
		String p = request.getParameter("pass");

		HttpSession session = request.getSession();
		session.setAttribute("username", u);

		Cookie cookie = new Cookie("username", u);
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);

		int typeOfUser = validate(u, p); // -1 = Wrong User/Pass ; 0 = Student ; 1 = Professor
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		switch (typeOfUser) {
		case -1:
			out.print("Sorry username or password error");
			dispatcher = request.getRequestDispatcher("/login-form.jsp");
			dispatcher.include(request, response);
			break;
		case 0: // Student
			dispatcher = request.getRequestDispatcher("StudentTodoServlet");
			dispatcher.forward(request, response);
			break;
		case 1: // Prof
			dispatcher = request.getRequestDispatcher("ProfTodoServlet");
			dispatcher.forward(request, response);
			break;
		default:
			out.print("Sorry there was an error logging in");
			dispatcher = request.getRequestDispatcher("/login-form.jsp");
			dispatcher.include(request, response);
			break;
		}

		out.close();
	}

	private int validate(String user, String pass) { // -1 = Wrong User/Pass ; 0 = Student ; 1 = Professor
		int status = -1;
		try {
			status = accountDBUtil.checkRole(user, pass);
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
