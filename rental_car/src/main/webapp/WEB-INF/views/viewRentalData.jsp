<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent Data</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
<c:choose>
<c:when test="${selectedRental!=null}">
	<div class="container">
		<div class="row">
				<h5>Requested data for Rent Id: ${selectedRental.getIdRent()}</h5>
				<div class="col-md-4">
					<table class="table table-striped-columns">
					<tr><td>Full name </td> <td>${person.getName()} ${person.getSurname()}</td></tr>
					<tr><td>Start Date </td> <td>${selectedRental.getStart_date()}</td></tr>
					<tr><td>End Date</td><td> ${selectedRental.getEnd_date()}</td></tr>
					<tr><td>Model</td><td> ${car.getModel()}</td></tr>
					<tr><td>License Plate</td><td> ${car.getLicense_plate()}</td></tr>
					<tr><td>Final Price</td><td> ${car.getPrice()}</td></tr>
					</table>
				</div>
		</div>
</div>
</c:when>
<c:when test="${selectedRental==null}">
<div class="container">
	<div class="row">
		<h4>No rent has been found</h4>
	</div>
</div>
</c:when>
</c:choose>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
	<a href="../" class="btn btn-primary">Home</a>
			</div>
		</div>
	</div>
</body>
</html>