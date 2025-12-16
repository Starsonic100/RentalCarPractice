<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guest User Form</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
<div class="container">

	<form:form action="submitForm" modelAttribute="person">
	<div class="row">
		<div class="col-md-4">
			<form:label path="name" class="form-label">Name:</form:label>
			<form:input path="name" id="name" class="form-control"/>
			<form:errors path="name" class="text-danger"/>
		</div>
		<div class="col-md-4">
			<form:label path="surname" class="form-label">Surname:</form:label>
			<form:input path="surname" class="form-control"/>
			<form:errors path="surname" class="text-danger"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<form:label path="email" class="form-label">Email:</form:label>
			<form:input path="email" class="form-control"/>
			<form:errors path="email" class="text-danger"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<form:label path="address"  class="form-label">Address:</form:label>
			<form:input path="address" class="form-control"/>
			<form:errors path="address" class="text-danger"/>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-4">
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</div>
	</div>
	</form:form>
</div>
</body>
</html>