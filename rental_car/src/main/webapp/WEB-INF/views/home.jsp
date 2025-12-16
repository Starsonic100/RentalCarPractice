<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rental Car</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="../../resources/css/style-sheet.css"/>
</head>
<header>
<jsp:include page="header.jsp" />
</header>
<body>
	<div class="container">
		<div class="row">
		<h3>Welcome! Please select an option</h3>
		<p><a href="person/viewPersonForm" class="link-primary">Book a car</a></p>
		<p><a href="rental/searchRentalForm" class="link-primary">Check reservation</a></p>
		<p><a href="rental/activeRents" class="link-primary">Return car</a></p>
		<p><a href="rental/inactiveRents" class="link-primary">Activate rent</a></p>
		<!-- Sign Up -->
		<!-- <br> -->
		<!-- Login -->
		<!-- <br>  -->
		</div>
	</div>
</body>
</html>