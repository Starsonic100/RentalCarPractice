<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent Created</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
	<div class="container">
		<div class="row">
		<h5>Thank you for using the rent service, this is the data related to your rent, you can use your Rent Id to search for your data</h5>
		<div class="col-md-4">
			<table class="table table-striped-columns">
			<tr><td>Rent Id</td><td> ${rental.getIdRent()} </td></tr>
			<tr><td>Full name</td><td> ${person.getName()} ${person.getSurname()}</td></tr>
			<tr><td>Start Date</td><td> ${car.getStartDate()}</td></tr>
			<tr><td>End Date</td><td> ${car.getEndDate()}</td></tr>
			<tr><td>Model</td><td> ${car.getModel()}</td></tr>
			<tr><td>License Plate</td><td> ${car.getLicensePlate()}</td></tr>
			<tr><td>Final Price</td><td> ${car.getPrice()}</td></tr>
			</table>
			<a href="../" class="btn btn-success">Finish and return to home</a>
			</div>
		</div>
	</div>
</body>
</html>