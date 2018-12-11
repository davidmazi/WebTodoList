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
 * Servlet implementation class EditAccountServlet
 */
@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDBUtil accountDBUtil;
	@Resource(name = "jdbc/webtodolist")
	private DataSource dataSource;
	int id;

	@Override
	public void init(ServletConfig config) throws ServletException {
		accountDBUtil = new AccountDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("accountId"));
		Account account = accountDBUtil.fetchAccount(id);
		request.setAttribute("Account", account);
		request.getRequestDispatcher("edit-account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		String role = request.getParameter("role");
		Account account = new Account(id, u, p, role);
		accountDBUtil.updateAccount(account);
		response.sendRedirect("TestServlet");
	}

}
