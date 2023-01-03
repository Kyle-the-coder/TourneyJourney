<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tourney Journey</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
</head>
<body class="bg-dark">

	<!-- Title Section -->
	<section>
		<div class="title p-3 border-bottom border-3 border-warning text-white" style="background-image:url(<c:url value="/imgs/tourneybg2.jpeg"/>)" >
			<h1 class="p-3 bg-warning rounded text-dark border border-dark border-3 title_logo">Welcome to Tourney Journey</h1>
			<div class="nav_links">
				<a href="/logout" class="bg-warning text-dark text-decoration-none p-2 rounded border border-3 border-dark my-2 nav_link_1">Logout</a>
				<a href="/dashboard" class="bg-warning text-dark text-decoration-none p-2 rounded border border-3 border-dark nav_link_2">Dashboard</a>
			</div>
		</div>
	</section>
	
	<div class="main_content my-3">
		<!-- Left Ad Space -->
		<section class="left_ad_space">
			<div class="mx-3">
				<img src="<c:url value="/imgs/adidasad.jpeg"/>" alt="adidas tourney ad" class="left_ad" />
			</div>			      
		</section>
			
		<!-- Form for Create Event -->	
		<section class="create_event border border-warning border-3 p-3">
			<div>
				<h3 class="p-3 bg-warning direction_text rounded border border-3 border-dark">Add an Event</h3>
				<form:form action="/create/new/event" class="form" method="POST" modelAttribute="newEvent" enctype="multipart/form-data">
					<div>
						<form:label path="eventName">Event Name:</form:label>
						<form:input path="eventName" type="text" class="form-control"/>
						<p><form:errors path="eventName"></form:errors></p>
					</div>
					
					<div>
						<form:label path="details">Details:</form:label>
						<form:input path="details" type="text" class="form-control"/>
						<p><form:errors path="details"></form:errors></p>
					</div>
					
					<div>
						<form:label path="type">Type:</form:label>
						<form:select path="type" class="form-control">
							<form:option value="videoGame">Video Game</form:option>
							<form:option value="danceEvent">Dance Event</form:option>
							<form:option value="boardGame">Board Game</form:option>
							<form:option value="food">Food Tourney</form:option>
							<form:option value="other">Other</form:option>
						</form:select>
						<p><form:errors path="type"></form:errors></p>
					</div>
					
					<div>
						<form:label path="date">Date:</form:label>
						<form:input path="date" type="date" class="form-control"/>
						<p><form:errors path="date"></form:errors></p>
					</div>
					
					<div>
						<form:label path="location">Location:</form:label>
						<form:input path="location" type="text" class="form-control"/>
						<p><form:errors path="location"></form:errors></p>
					</div>
					
					<div>
						<form:label path="photoFile">Flyer:</form:label>
						<form:input type="file" path="photoFile" accept="image/png, image/jpeg" />
					</div>
					
					<form:hidden path="user" value="${ userId }"/>
					
					<form:button type="submit" class="p-2 my-2 rounded bg-warning">Submit</form:button>
				
				</form:form>
				<a href="/upload/img">Upload Event Image Here</a>
			</div>	
		</section>
			
		<!-- Right Ad Space -->	
		<section class="right_ad_space">
			<div class="mx-3">
				<img src="<c:url value="/imgs/dancead.jpeg"/>" alt="dance tourney ad" class="right_ad "/>
			</div>
		</section>
	</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>