<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<c:if test="${sessionScope.username == 'Nada'}">
<head>
<title>Web Account Tracker</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>TODO LIST Welcome ${sessionScope.username}</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form action="LogoutServlet" method="get">
				<input type="submit" value="Logout" />
			</form>
			<form action="AddTodoServlet" method="get">
				<input type="submit" value="Add Todo" />
			</form>
			<table>
				<tr>
					<th>Description</th>
				</tr>
				<c:forEach var="tempTodo" items="${TODO_LIST }">
					<c:url var="EditTodoLink" value="EditTodoServlet">
						<c:param name="todoItemId" value="${tempTodo.idtodo}" />
					</c:url>
					<c:url var="DeleteTodoLink" value="DeleteTodoServlet">
						<c:param name="todoItemId" value="${tempTodo.idtodo}" />
					</c:url>
					<tr>
						<td>${tempTodo.description}</td>
						<td><a href="${EditTodoLink }"> Edit</a>|<a href="${DeleteTodoLink }">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</c:if>
</html>