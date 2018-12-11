package com.WebTodoList.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

private AccountDBUtil accountDBUtil;
	
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;


	@Override
	public void init() throws ServletException {
		super.init();
		accountDBUtil = new AccountDBUtil(dataSource);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		try {
			listAccounts(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		Account account = new Account(username, password, role);
		accountDBUtil.addAccount(account);
		try {
			listAccounts(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listAccounts(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		List<Account> accounts = accountDBUtil.getAccounts();
		request.setAttribute("ACCOUNT_LIST", accounts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-accounts.jsp");
		dispatcher.forward(request, response);
	}

}
