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
    <form action="login" method="post">
        <h2 class="text-center">Please Sign In</h2>
        
        <c:if test="${loginFail }">
        <div class="mb-3">
        <span class="alert alert-danger">Username or password is incorrect</span>
        </div>
        </c:if>
        
        <input type="hidden" name="mode" value="SIGNIN" >
        
       
        <div class="mb-3">
            <label for="email" class="form-label">Enter Your Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" class="form-control" required="required" autofocus>
	    </div>
        
        <div class="mb-3">
            <label for="password" class="form-label">Enter Your Password</label>
				 <input type="password" id="password" name="password" placeholder="Enter your password" class="form-control" required="required" autofocus>
		</div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
    <p> Don't have an account ?<a href="signup.jsp">Please Sign Up</a> </p>
    
	</div> <!-- ./container -->
        
        
        <c:import url="common/footer.html"></c:import>
        
</body>
</html>