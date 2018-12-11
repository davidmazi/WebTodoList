package com.WebTodoList.web.jdbc;

public class TodoItem {
	int idtodo;
	String description;
	
	public int getIdtodo() {
		return idtodo;
	}
	public void setIdtodo(int idtodo) {
		this.idtodo = idtodo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TodoItem(int idtodo, String description) {
		super();
		this.idtodo = idtodo;
		this.description = description;
	}
	public TodoItem(int idtodo) {
		super();
		this.idtodo = idtodo;
	}
	public TodoItem(String description) {
		super();
		this.description = description;
	}
	@Override
	public String toString() {
		return "TodoItem [idtodo=" + idtodo + ", description=" + description + "]";
	}
	
	
}
