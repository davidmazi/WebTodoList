<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/add-account-style.css">
<link type="text/css" rel="stylesheet" href="css/style.css">
<title>Add a Student</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>ESILV Engineer School</h2>
		</div>
	</div>
	<div id="container">
		<h3>Add New Account</h3>
		<form action="TestServlet" method="post">
			<table>
				<tbody>
					<tr>
						<td><label>Username: </label></td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td><label>Password: </label></td>
						<td><input type="text" name="password" /></td>
					</tr>
					<tr>
						<td><label>Role: </label></td>
						<td><input type="text" name="role" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<a href="TestServlet">Back to List</a>
	</div>
</body>
</html>