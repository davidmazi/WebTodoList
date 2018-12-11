<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web Account Tracker</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<form action="LoginServlet" method="post">
	<div id="wrapper">
		<div id="header">
			<h2>LOGIN</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			Please enter your username : <input type="text" name="user" /> <br>

			Please enter your password : <input type="text" name="pass" /> <input
				type="submit" value="submit">
		</div>
	</div>
</form>
</html>
