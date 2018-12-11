<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
			<table>
				<tr>
					<th>Description</th>
				</tr>
				<c:forEach var="tempTodo" items="${TODO_LIST }">
					<tr>
						<td>${tempTodo.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>