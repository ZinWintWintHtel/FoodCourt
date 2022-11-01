<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<c:import url="common/header.html"></c:import>
    <title>Food Court</title>
<body>
	<!-- Responsive navbar-->
        <c:import url="common/nav-bar.jsp"></c:import>
        
        
    <div class="container">
    <form action="drink" method="post">
        <h2 class="text-center">Add New Drink Menu</h2>
        
        <input type="hidden" name="mode" value="CREATE">
        
        <div class="mb-3">
            <label for="name" class="form-label">Enter New Drink Menu Name</label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" placeholder="New Drink Menu Name" class="form-control" autofocus>
            </div>
        </div>
        
        <div class="mb-3">
            <label for="price" class="form-label">Enter Drink Price</label>
            <div class="col-sm-9">
                <input type="text" id="price" name="price" placeholder="Drink Price" class="form-control" autofocus>
            </div>
        </div>
        
        
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
	</div> <!-- ./container -->
        
        
        <c:import url="common/footer.html"></c:import>
</body>
</html>