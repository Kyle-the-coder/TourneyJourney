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
				<a href="/create/new/event" class="bg-warning text-dark text-decoration-none p-2 rounded border border-3 border-dark nav_link_2">Create Event</a>
			</div>
		</div>
	</section>
	
	<div class="main_content my-3">
		<!-- Your Events / Participated Section -->
		<section class="p-3 border border-5 border-warning mx-3 left_section">
		
			<!-- Your Posted Events List -->
			<div class="your_events">
				<h5 class="p-3 bg-warning rounded text-dark direction_text border border-3 border-dark">Your Events</h5>
				<table class="table table-bordered border-dark border-3 table-hover m-0 bg-dark text-white table_shadow">
				  <thead>
				    <tr>
				    	<th class="bg-warning text-dark border border-2 border-dark">Name</th>
				    	<th class="bg-warning text-dark border border-2 border-dark">Action</th>
					</tr>
				  </thead>
				  <tbody>
				    <tr>
				       <c:forEach var="oneEvent" items="${ userEventList }">
				       		
							    <tr>
							      <td><p class="text-white"><c:out value="${ oneEvent.eventName }"/></p></td>
							      <td><a href="/view/one/event/${ oneEvent.id }">View</a></td>
							    </tr>
				       		
				  		</c:forEach>
				    </tr>
				  </tbody>
				</table>
			</div>
			
			<!-- Participated Events List -->
			<div class="participated_events my-3">
				<h5 class="p-3 bg-warning rounded text-dark direction_text border border-3 border-dark">Participated</h5>
				<table class="table table-bordered table-hover m-0 bg-dark text-white table_shadow border-dark">
				  <thead>
				    <tr>
				    	<th class="bg-warning text-dark border border-2 border-dark">Name</th>
				    	<th class="bg-warning text-dark border border-2 border-dark">Action</th>
					</tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>
				      	<p class="text-white">Fortnite Fight 5</p>
				      	<p class="text-white">Chess of Champions</p>
				      </td>
				      <td>
				      	<p class="text-white"><a href="#">View</a></p>
				      	<p class="text-white"><a href="#">View</a></p>
				      </td>
				    </tr>
				  </tbody>
				</table>
			</div>
			
			<div>
				<img src="<c:url value="/imgs/db1.jpeg"/>" alt="nike ad" class="my-5 ad_space1 rounded" />
			</div>
		</section>
				
				
				
				
				
			   
				
		<!-- Welcome Center Section -->
		<section class="p-3 border border-5 border-info welcome_content middle_section">
		
			<!-- User Image -->
			<div class="welcome text-white">
				<h3 class="p-3 rounded bg-info text-dark direction_text border border-3 border-dark">Welcome <c:out value="${ userName }"/></h3>
				<p class="text-dark">Your latest event:</p>
			</div>
			<div class="user_img">
				<img src="<c:url value="/imgs/tournyflyer1.webp"/>"  alt="dance comp" class="user_img_1 rounded" />
			</div>
			
			<!-- Likes Button -->
			<div class="likes my-2">
				<button class="bg-warning rounded like_button border border-3 border-dark"> Like </button>
				<p class="rounded bg-info p-2 border border-3 border-dark table_shadow">Likes: 23</p>
			</div>
			
			<!-- Comment Section -->
			<div class="comments">
				<h6 class="">Comments:</h6>
				<div class="comment_box bg-light p-2 rounded border border-3 border-dark table_shadow">
					<p class="p-0"><span class="text-primary">User1:</span> Love this event!</p>
					<p class="p-0"><span class="text-danger">User2:</span> Best event ever!</p>
				</div>
					
				<label for="comments" class="my-1">Leave a Comment:</label>
				<br>
				<textarea name="comments" rows="5" class="form-control table_shadow my-1 border border-3 border-dark"></textarea>
				<button class="bg-warning rounded p-2 my-2 table_shadow border border-3 border-dark">Add Comment</button>
			</div>
		</section>
			      
			      
		<!-- Upcoming Events + Ad space Section -->
		<section class="p-3 border border-5 border-warning mx-3 upcoming_content">
			<div class="upcoming_events text-white">
				<h5 class="p-3 bg-warning rounded text-dark direction_text border border-3 border-dark">Upcoming Events</h5>
				<table class="table table-bordered border-dark table-hover m-0 bg-dark text-white table_shadow">
				  <thead>
				    <tr>
				    	<th class="bg-warning text-dark border border-2 border-dark">Name</th>
				    	<th class="bg-warning text-dark border border-2 border-dark">Action</th>
					</tr>
				  </thead>
				  <tbody>
				  <c:forEach var="oneEvent" items="${ eventList }">
				    <tr>
				      <td><p class="text-white"><c:out value="${ oneEvent.eventName }"/></p></td>
				      <td><a href="/view/one/event/${ oneEvent.id }">View</a></td>
				    </tr>
				  </c:forEach>
				  </tbody>
				</table>
				
				<img src="<c:url value="/imgs/nikead.png"/>" alt="nike ad" class="my-5 ad-space rounded" />
				
			</div>
			
		</section>
	</div>
			      
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>