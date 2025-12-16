<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select a car</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
	<c:choose>
	<c:when test="${!cars.isEmpty()}">
	<div class="container">
		<div class="row">
			<h5>Select the ${car.type} car that you wish to rent</h5>
				<form:form action="selectCar" modelAttribute="car">
					<div class="col-md-4">
					<table class="table table-striped-columns">
						<tr>
							<td>Select</td>
							<td>Model</td>
							<td>License Plate</td>
							<td>Price</td>
						</tr>
						<c:forEach  items="${cars}" var="carOption">
							<tr>
							<td><form:radiobutton path="idCars" value="${carOption.getIdCars()}"/></td>
							<td><c:out value="${carOption.getModel()}"/></td>
							<td><c:out value="${carOption.getLicense_plate()}"/></td>
							<td>$<c:out value="${carOption.getPrice()}"/></td>
							</tr>
						</c:forEach>
					</table>
					</div>
					<form:errors path="idCars" class="text-danger"/>
					<br>
					<input type="submit" name="cancel" value="Return to previous page" class="btn btn-secondary"/>
					<input type="submit" name="submit" value="Rent Car" class="btn btn-primary"/>
				</form:form>
		</div>
	</div>	
	</c:when>
	<c:when test="${cars.isEmpty()}">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
			<form:form action="selectCar" modelAttribute="car">
				<h4>Sorry, no cars are available for the selected dates</h4><br>
				<input type="submit" name="cancel" value="Return to previous page"/>
			</form:form>
			</div>
		</div>
	</div>
	</c:when>
	</c:choose>
</body>
</html>