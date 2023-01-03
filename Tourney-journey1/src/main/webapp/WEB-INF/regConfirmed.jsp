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
				<a href="#register" class="bg-warning text-dark text-decoration-none p-2 rounded border border-3 border-dark my-2 nav_link_1">Register</a>
				<a href="#register" class="bg-warning text-dark text-decoration-none p-2 rounded border border-3 border-dark nav_link_2">Login</a>
			</div>
		</div>
		
	</section>
	
	<!-- Reg Confirmed Section -->
	<section>
		<div class="reg_confirmed text-white p-3">
			<h2 class="mx-3">Registration Confirmed! </h2>
			<a href="#register" class="text-decoration-none border border-3 rounded bg-warning text-dark p-2">Login Here</a>
		</div>
	</section>
	
	
	<!-- Forbes Quote Section -->
	<section class="p-3 my-3 border-top border-bottom border-3 border-info quote_section" style="background-image:url(<c:url value="/imgs/chess.webp"/>)">
		<div class="quote text-white">
			<h2 class="bg-dark rounded p-4 border border-3 border-info quote_font">"<span class="text-info quote_text">Best Tourney App on the Internet</span>" -Forbes</h2>
		</div>
	</section>
	
	
	<!-- About Section -->
	<section class="bg-warning border-top border-bottom border-3">
		<div class="about p-3">
			<div class="about_p">
				<h4 class="text-decoration-underline about_title bg-warning p-2">About Tourney Journey:</h4>
				<p class="bg-warning">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nullam vehicula ipsum a arcu. Vel fringilla est ullamcorper eget nulla facilisi etiam dignissim. Nisi scelerisque eu ultrices vitae auctor eu. Tempor nec feugiat nisl pretium fusce. Gravida in fermentum et sollicitudin ac orci. Velit dignissim sodales ut eu. Scelerisque varius morbi enim nunc. Fringilla phasellus faucibus scelerisque eleifend. Dui accumsan sit amet nulla.</p>
			</div>
			<div class="about_img_p">
				<img src="<c:url value="/imgs/smashbros.jpeg"/>" alt="winning trophy" class="about_img"/>
				<img src="<c:url value="/imgs/dancebg1.png"/>"  alt="dance comp" class="img_1" />
			</div>
		</div>
	</section>
	
	<!-- Direction Section -->
	<section class="p-3">
		<div class="direction text-white">
			<h2 class="direction_text">Register or Log in </h2>
		</div>
	</section>
	
	
	
	<!-- Reg and Login Section -->
	<section>
		<div class="content text-white">
			<div class="content_2">
				<h3 class="bg-warning p-2 rounded text-dark" id="register">Register</h3>
					<form:form action="/register" method="POST" modelAttribute="newUser" class="form">
						<div>
							<form:label path="userName">Name:</form:label>
							<form:input type="text" path="userName" class="form-control"></form:input>
							<p><form:errors path="userName"></form:errors></p>
						</div>
						<div>
							<form:label path="email">Email:</form:label>
							<form:input type="text" path="email"  class="form-control"></form:input>
							<p><form:errors path="email"></form:errors></p>
						</div>
						<div>
							<form:label path="password">Password:</form:label>
							<form:input type="password" path="password"  class="form-control formBottom"></form:input>
							<p><form:errors path="password"></form:errors></p>
						</div>
						<div>
							<form:label path="confirm">Confirm Password:</form:label>
							<form:input type="password" path="confirm"  class="form-control formBottom"></form:input>
							<p><form:errors path="confirm"></form:errors></p>
						</div>
					
						<button type="submit" class="bg-warning p-2 border border-2 border-light rounded">Submit</button>
					</form:form>
			</div>
			
			
			<div class="content_2">
				<h3 class="bg-warning p-2 rounded text-dark">Login</h3>
				<form:form action="/login" method="POST" modelAttribute="newLogin" class="form">
					<div>
						<form:label path="email">Email:</form:label>
						<form:input type="text" path="email"  class="form-control"></form:input>
						<p><form:errors path="email"></form:errors></p>
					</div>
					<div>
						<form:label path="password">Password:</form:label>
						<form:input type="password" path="password"  class="form-control formBottom"></form:input>
						<p><form:errors path="password"></form:errors></p>
					</div>
				
					<button type="submit" class="bg-warning p-2 border border-2 border-light rounded">Submit</button>
				</form:form>
			</div>
		</div>
	</section>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>