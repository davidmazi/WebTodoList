package com.WebTodoList.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TodoItemDBUtil {

	private DataSource dataSource;
	
	public TodoItemDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<TodoItem> getTodoItems() throws Exception {
		List<TodoItem> todoItems= new ArrayList<TodoItem>();
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todo";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				int idtodo = myRs.getInt("idtodo");
				String description=myRs.getString("description");
				TodoItem tempTodo= new TodoItem(idtodo,description);
				todoItems.add(tempTodo);
			}
			return todoItems;
		} finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	public void addTodoItem(TodoItem todoItem) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "insert into todo (description) values (?)";
			myStmt = myConn.prepareStatement(sql);
			String description = todoItem.getDescription();
			myStmt.setString(1, description);
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public TodoItem fetchTodoItem(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		TodoItem todoItem = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "select * from todo where idtodo=" + id;
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				String description = myRs.getString("description");
				todoItem = new TodoItem(description);
			}
			return todoItem;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public void updateTodoItem(TodoItem todoItem) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update todo set description=? where idtodo=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, todoItem.getDescription());
			myStmt.setInt(2, todoItem.getIdtodo());
			myStmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteTodoItem(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "delete from todo where idtodo=" + id;
			myStmt.execute(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(myConn, myStmt, null);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
			if(myConn!=null)
				myConn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
