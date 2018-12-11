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
import javax.sql.DataSource;

/**
 * Servlet implementation class ProfTodoServlet
 */
@WebServlet("/ProfTodoServlet")
public class ProfTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TodoItemDBUtil todoItemDBUtil;
	@Resource(name = "jdbc/webtodolist")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		todoItemDBUtil = new TodoItemDBUtil(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listTodos(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter("description");
		if (description != null) {
			TodoItem todoItem = new TodoItem(description);
			todoItemDBUtil.addTodoItem(todoItem);
		}
		try {
			listTodos(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listTodos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TodoItem> todos = todoItemDBUtil.getTodoItems();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-profs-todo.jsp");
		dispatcher.forward(request, response);
	}

}
