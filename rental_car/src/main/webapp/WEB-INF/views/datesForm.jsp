<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select dates</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
<div class="container">
		<div class="row">
			<h5>Select the dates on which you want to rent a car and the type of car</h5>
		</div>
				<form:form action="../car/submitDateForm" modelAttribute="car">
				<div class="row">
				<div class="col-md-6">
					<form:label path="startDate" class="form-label">Start Date</form:label>
					<form:input type="date" path="startDate" class="form-control"/>
					<form:errors path="startDate" class="text-danger"/>
					<form:errors path="" class="text-danger"/>
				</div>
				<div class="col-md-6">
					<form:label path="endDate" class="form-label">End Date</form:label>
					<form:input type="date" path="endDate" class="form-control"/>
					<form:errors path="endDate" class="text-danger"/>
				</div>
				<div class="col-md-4">
		 			<form:label path="type"class="form-label">Type of Car</form:label>
						<form:select path="type" class="form-select" >
						    <form:option value="-" label="--Please Select--"/>
					        <form:option value="Economic" label="Economic"/>
						    <form:option value="Standard" label="Standard"/>
						    <form:option value="Premium" label="Premium"/>
						</form:select>
					<form:errors path="type" class="text-danger" />
				</div>
				</div>
				<div class="row">
			<form:label path="sortCars"class="form-label">Sort by price in:</form:label>
				<form:radiobutton path="sortCars" value="0"/><form:label path="sortCars"class="form-check-label">Descendant Order</form:label>
				<form:radiobutton path="sortCars" value="1"/><form:label path="sortCars"class="form-label">Ascendant Order</form:label>
			<input type="submit" value="Find" class="btn btn-primary"/>
			</div>
			</form:form>
	</div>
</body>
</html>