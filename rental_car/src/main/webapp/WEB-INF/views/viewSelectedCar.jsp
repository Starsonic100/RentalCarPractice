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
<div class="container">
		<div class="row">
			<h5>Current data for rent</h5>
		</div>
		<div class="row">
			<div class="col-md-4">
				<table class="table table-striped">
					<tr><td>Full name: ${person.getName()} ${person.getSurname}</td></tr>
						<tr><td>Start Date: ${car.getStartDate()}</td></tr>
						<tr><td>End Date: ${car.getEndDate()}</td></tr>
						<tr><td>Model: ${car.getModel()}</td></tr>
						<tr><td>License Plate: ${car.getLicensePlate()}</td></tr>
						<tr><td>Final Price: ${car.getPrice()}</td></tr>
				</table>
			</div>
			</div>
		<div class="row">
			<h6>Do you wish to proceed with payment?</h6>
		</div>
			<div class="row">
				<div class="col-md-2">
	
<form:form action="submitDateForm" modelAttribute="car">
				<input type="submit" name="cancel" value="Return"  class="btn btn-secondary"/>
</form:form>
			</div>
			<div class="col-md-2">

<form:form action="../rental/createRent" modelAttribute="car">
			<input type="submit" name="submit" value="Pay" class="btn btn-success"/>
</form:form>
			</div>

</div>
</div>
</body>
</html>