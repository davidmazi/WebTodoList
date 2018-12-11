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
 * Servlet implementation class EditTodoServlet
 */
@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TodoItemDBUtil todoItemDBUtil;
	@Resource(name = "jdbc/webtodolist")
	private DataSource dataSource;
	int idtodo;

	@Override
	public void init(ServletConfig config) throws ServletException {
		todoItemDBUtil = new TodoItemDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		idtodo = Integer.parseInt(request.getParameter("todoItemId"));
		TodoItem todoItem = todoItemDBUtil.fetchTodoItem(idtodo);
		request.setAttribute("TodoItem", todoItem);
		request.getRequestDispatcher("edit-todoitem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String d = request.getParameter("description");
		TodoItem todoItem = new TodoItem(idtodo,d);
		todoItemDBUtil.updateTodoItem(todoItem);
		response.sendRedirect("ProfTodoServlet");
	}

}
