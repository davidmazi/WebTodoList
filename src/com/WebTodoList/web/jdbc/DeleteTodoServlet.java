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
 * Servlet implementation class DeleteTodoServlet
 */
@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TodoItemDBUtil todoItemDBUtil;
	
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		todoItemDBUtil = new TodoItemDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idtodo=Integer.parseInt(request.getParameter("todoItemId"));
		todoItemDBUtil.deleteTodoItem(idtodo);
		response.sendRedirect("ProfTodoServlet");
	}

}
