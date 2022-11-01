<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<c:import url="common/header.html"></c:import>
    <title>Food Court</title>
<body>
		<!-- Responsive navbar-->
  
        
        
    <div class="container">
    <form action="user" method="post">
        <h2 class="text-center">User Registration</h2>
        
        <input type="hidden" name="mode" value="SIGNUP">
        
        <div class="mb-3">
            <label for="username" class="form-label">Enter User Name</label>
            <input type="text" id="username" name="username" placeholder="New Username" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="email" class="form-label">Enter Your Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" class="form-control" required="required" autofocus>
	    </div>
        
        <div class="mb-3">
            <label for="password" class="form-label">Enter Your Password</label>
				 <input type="text" id="password" name="password" placeholder="Enter your password" class="form-control" required="required" autofocus>
		</div>
        
        <div class="mb-3 form-check">
        	<input type="checkbox" class="form-check-input" id="role" name="role" value="true">
            <label class="form-check-label" for="role" >Admin</label>
        </div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
    <p> Already has an account ?<a href="signin.jsp">Please Sign In</a> </p>
    
	</div> <!-- ./container -->
        
        
        <c:import url="common/footer.html"></c:import>
        
</body>
</html>