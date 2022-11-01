<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#"><img id="logo"
				src="assets/foodcourtlogo.jpg" alt="logo" /> FoodCourt</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" aria-current="page" href="/FoodCourt/food">Food</a></li>
                        <li class="nav-item"><a class="nav-link" href="/FoodCourt/drink">Drink</a></li>
                        <li class="nav-item"><a class="nav-link" href="/FoodCourt/snack">Snack</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false"><c:out value="${user.username }"></c:out></a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="food?mode=LOGOUT">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
