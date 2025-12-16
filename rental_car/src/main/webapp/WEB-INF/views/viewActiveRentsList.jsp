<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select a car to return</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
	<c:choose>
	<c:when test="${!rents.isEmpty()}">
		<div class="container">
		<div class="row">
		<h5>Select the car that you want to return. This action will move the end date for the rent.</h5>
		</div>
		<form:form action="selectRent" modelAttribute="rental">
		<div class="row">
		<div class="col-md-4">
			<table class="table table-striped-columns">
				<tr>
					<td>Select</td>
					<td>Model</td>
					<td>License Plate</td>
					<td>Customer</td>
					<td>Start Date</td>
					<td>Current End Date</td>
				</tr>
				<c:forEach  items="${rents}" var="rentOption">
					<tr>
					<td><form:radiobutton path="idRent" value="${rentOption.getIdRent()}"/></td>
					<td><c:out value="${rentOption.getCar().getModel()}"/></td>
					<td><c:out value="${rentOption.getCar().getLicense_plate()}"/></td>
					<td><c:out value="${rentOption.getPerson().getName()}"/> <c:out value="${rentOption.getPerson().getSurname()}"/></td>
					<td><c:out value="${rentOption.getStart_date()}"/></td>
					<td><c:out value="${rentOption.getEnd_date()}"/></td>
					</tr>
				</c:forEach>
			</table>
				<form:errors path="idRent" class="text-danger"/>
			</div>
			<div class="row">
				<div class="col-md-4">
				<a href="../" class="btn btn-secondary">Cancel</a>
				<input type="submit" name="submit" value="Return Car" class="btn btn-primary"/>		
				</div>
				</div>		
		</div>
		</form:form>
		</div>
	</c:when>
	<c:when test="${rents.isEmpty()}">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
		<form:form action="selectRent" modelAttribute="rental">
			<h4>No rents are currently active</h4><br>
			<a href="../" class="btn btn-primary">Home</a>
		</form:form>
		</div>
		</div>
		</div>
	</c:when>
	</c:choose>
</body>
</html>