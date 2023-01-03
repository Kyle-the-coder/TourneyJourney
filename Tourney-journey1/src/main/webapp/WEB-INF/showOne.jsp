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
	
	
	
	<div class="main_content_1 m-3 p-3 border border-5 border-warning">
	
	
	<!-- Left Side Details / Comment Section -->
	<section class="left_side">
		<div>
		
			<!-- Event Title -->
			<div class="left_title">
				<h3 class="p-3 border border-3 border-dark rounded bg-warning direction_text"><c:out value="${ event.eventName }"></c:out> Details:</h3>
			</div>
			
			<!-- Event Information / Participants -->
			<div class="slide_content text-dark border border-3 border-dark bg-warning rounded p-3 table_shadow">
				<h6>Hosted By: <c:out value="${ event.user.userName }"/> </h6>
				<h6>Date: <c:out value="${ event.date }"/> </h6>
				<h6>Details: <c:out value="${ event.details }"/> </h6>
				<h6>Participants:</h6>
				<c:forEach var="onePart" items="${ partList }">
					<h6>-<c:out value="${ onePart.userName }"/></h6>
				</c:forEach>
				
			</div>
			
			<!-- Likes Section -->
			<div class="likes my-2">
				
					
				<!-- Like / Unlike logic -->
				<c:if test="${ !event.user.id.equals(userId) }">
					<c:choose>
				
				
					<c:when test="${ event.users_liked.contains(loggedInUser) }">
						<form action="/unLike/${ event.id }" method="POST">
							<input type="hidden" name="_method" value="PUT"  />
							<button type="submit" class="text-decoration-none text-warning border border-2 border-warning rounded bg-dark p-2 table_shadow">Unlike</button>
						</form>
					</c:when>
				
			
					<c:otherwise>
						<form action="/likes/${ event.id }" method="POST">
							<input type="hidden" name="_method" value="PUT"  />
							<button type="submit" class="text-decoration-none p-2 table_shadow border border-2 border-dark bg-warning text-dark rounded">Like</button>
						</form>
					</c:otherwise>
					
					</c:choose>
				</c:if>
				
				
				
				<p class="rounded bg-info p-2 border border-3 border-dark table_shadow">Likes: <c:out value="${ event.likes }"/></p>
			</div>
				
			<!-- Comment Section -->
			<div class="comments my-2">
				<h6 class="">Comments:</h6>
				<div class="comment_box bg-light p-2 rounded border border-3 border-dark table_shadow">
					<c:forEach var="oneComment" items="${ commentList }">
					<p class="p-0"><span class="text-primary"> 
						<c:out value="${ oneComment.userComment.userName }"/>: </span>
						<c:out value="${ oneComment.comments }"/>
					</p>
					</c:forEach>
				</div>
					
				<!-- Logic for Comment -->
				<c:if test="${ !event.user.id.equals(userId) }">
				
					<form:form action="/comment/${ event.id }" method="POST" modelAttribute="newComment">
						<form:label path="comments" class="my-1">Leave a Comment:</form:label>
						<br>
						
						<form:textarea path="comments" rows="5" class="form-control my-1 border border-3 border-dark table_shadow"></form:textarea>
						<p><form:errors path="comments"></form:errors></p>
						
						<div class="buttons_2">
							<form:button type="submit" class="bg-warning rounded p-2 my-2 table_shadow border border-3 border-dark">Add Comment</form:button>
						</div>
						
						
						<form:hidden path="userComment" value="${ userId }"/>
						<form:hidden path="eventComment" value="${ event.id }"/>
						
					</form:form>
				</c:if>
					
					<!-- Sign Up Form -->
					<c:choose>
					
						<c:when test="${ !partList.contains(loggedInUser) }">
							<form:form action="/sign/up/${ event.id }" method="POST" modelAttribute="signUp">
								<div class="buttons">
									<form:input type="hidden" path="participantName" value="${ userName }"></form:input>
									<form:hidden path="userParticipant" value="${ userId }"/>
									<form:hidden path="eventParticipant" value="${ event.id }"/>
									<form:button type="submit" class="bg-info rounded p-2 my-2 table_shadow border border-3 border-dark mx-3">Sign Up</form:button>
								</div>
							</form:form>
						</c:when>
						
						<c:otherwise>
							<form:form action="/delete/part/${ event.id }" method="POST" modelAttribute="signUp">
							<input type="hidden" name="_method" value="put"/>
								<div class="buttons">
									<form:input type="hidden" path="participantName" value="${ userName }"></form:input>
									<form:hidden path="userParticipant" value="${ userId }"/>
									<form:hidden path="eventParticipant" value="${ event.id }"/>
									<form:button type="submit" class="bg-info rounded p-2 my-2 table_shadow border border-3 border-dark mx-3">Retract Sign Up</form:button>
								</div>
							</form:form>
							
						</c:otherwise>
					
					</c:choose>
				
				<!-- Logic for Edit Delete -->
				<c:if test="${ event.user.id.equals(userId) }">
					<div class="buttons_1">
						<a href="/edit/event/${event.id}" class="a_tag bg-warning rounded text-center text-dark text-decoration-none m-2 p-2 table_shadow border border-3 border-dark">Edit Your Event</a>
						<form method="post" action="/delete/${ event.id }">
				      		<input type="hidden" name="_method" value="delete"/>
				      		<button type="submit" class="text-decoration-none text-warning border border-2 table_shadow border-warning rounded bg-dark my-2 p-3">Delete</button>
						</form>
					</div>
				</c:if>
				
				
				
			</div>
		</div>
	</section>
		
		
		
	<!-- Right Side Img and Sign Up / Delete / Ad Space -->	
	<section class="right_side">
		<div>
			<img src="<c:url value="${ event.photosImagePath }"/>" alt="" class="user_img border rounded table_shadow border-dark"/>
		
		</div>
	</section>
	</div>
		
		
		
		
		
		
		
		
		
		


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>