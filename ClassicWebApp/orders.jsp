<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="customer" class="classic.web.app.CustomerBean" scope="session"/>
<jsp:setProperty name="customer" property="*"/>
<html>
	<head>
		<title>ClassicWebApp</title>
	</head>
	<body>
		<h1>Welcome  ${customer.id}</h1>
		<h3>Departments</h3>
		<table border="1">
			<tr>
				<th>Department No</th>
				<th>Department Name</th>
				<th>Location</th>
			</tr>
			<c:forEach var="entry" items="${customer.dept}">
				<tr>
					<td>${entry.did}</td>
					<td>${entry.dname}</td>
					<td>${entry.loc}</td>
				</tr>
			</c:forEach>
		</table>
		<form method = "POST" action = "emp.jsp">
			<label for="Department_name"> Department Id</label>
			<input type= "text" name ="did" > 
			<br>
			<input type = "submit" name = "submit" value = "submit">
			
		</form>

		<p>
			<a href="customer2.jsp?signout=true">Logout</a>
		</p>
	</body>
</html>

