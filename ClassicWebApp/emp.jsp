<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="customer" class="classic.web.app.CustomerBean" scope="session"/>
 <jsp:setProperty name="customer" property="*"/> 
<html>
	<head>
		<title>ClassicWebApp</title>
	</head>
	<body>
		<h1>Welcome  ${customer.did}</h1>
		<h3>Employee</h3>
		<table border="1">
			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Salary</th>
				<th>Age</th>
				<th>Department Id</th>
			</tr>
			<c:forEach var="entry" items="${customer.emp}">
				<tr>
					<td>${entry.eid}</td>
					<td>${entry.ename}</td>
					<td>${entry.sal}</td>
					<td>${entry.age}</td>
					<td>${entry.did}</td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<a href="customer2.jsp?signout=false">Logout</a>
		</p>
	</body>
</html>

