<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web Account Tracker</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<%-- 	${ACCOUNT_LIST} --%>
	<div id="wrapper">
		<div id="header">
			<h2>LIST OF ACCOUNTS</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form action="AddAccountServlet" method="get">
				<input type="submit" value="Add Account" />
			</form>
			<table>
				<tr>
					<th>Account</th>
					<th>Password</th>
					<th>Role</th>
				</tr>
				<c:forEach var="tempAccount" items="${ACCOUNT_LIST }">
					<c:url var="EditLink" value="EditAccountServlet">
						<c:param name="accountId" value="${tempAccount.id}" />
					</c:url>
					<c:url var="DeleteLink" value="DeleteAccountServlet">
						<c:param name="accountId" value="${tempAccount.id}" />
					</c:url>
					<tr>
						<td>${tempAccount.username}</td>
						<td>${tempAccount.password}</td>
						<td>${tempAccount.role}</td>
						<td><a href="${EditLink }"> Edit</a>|<a href="${DeleteLink }">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>