<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search your rented car</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<header>
	<jsp:include page="header.jsp" />
</header>
<body>
	 <div class="container">

	<form:form action="searchRent" modelAttribute="rental" method="GET">
		<div class="row">
			<div class="col-md-4">
			<form:label path="idRent" class="form-label">Insert the id of your rent:</form:label>
			<form:input path="idRent" class="form-control"/>
			<form:errors path="idRent" class="text-danger"/>
			<br>
			<input type="submit" value="Search" class="btn btn-primary"/>
            </div>
		</div>
	</form:form>
		</div>
	
</body>
</html>