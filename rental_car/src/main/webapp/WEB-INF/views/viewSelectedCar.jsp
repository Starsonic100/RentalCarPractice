<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Selected Car</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
<h1>Current data for rent</h1>
<table>
<tr><td>Full name: ${person.getName()}</td></tr>
<tr><td>Start Date: ${car.getStartDate()}</td></tr>
<tr><td>End Date: ${car.getEndDate()}</td></tr>
<tr><td>Model: ${car.getModel()}</td></tr>
<tr><td>License Plate: ${car.getLicensePlate()}</td></tr>
<tr><td>Final Price: ${car.getPrice()}</td></tr>
</table>
Do you wish to proceed with payment?
<form:form action="submitDateForm" modelAttribute="car">
<table>
	<tr><td><input type="submit" name="cancel" value="Return"/></td></tr>
</table>
</form:form>
<form:form action="../rental/createRent" modelAttribute="car">
<table>
	<tr><td><input type="submit" name="submit" value="Pay"/></td></tr>
</table>
</form:form>
</body>
</html>