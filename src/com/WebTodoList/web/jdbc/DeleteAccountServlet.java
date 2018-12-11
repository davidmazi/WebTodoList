package com.WebTodoList.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDBUtil accountDBUtil;
	
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		accountDBUtil = new AccountDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("accountId"));
		accountDBUtil.deleteAccount(id);
		response.sendRedirect("TestServlet");
	}

}
