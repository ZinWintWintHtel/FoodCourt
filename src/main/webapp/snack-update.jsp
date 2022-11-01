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
    <form action="snack" method="post">
        <h2 class="text-center">Update Snack Menu</h2>
        
        <input type="hidden" name="mode" value="UPDATE">
        <input type="hidden" name="id" value="${snack.id }">
        
        <h3> <c:out value="ID : ${snack.id}"></c:out> </h3>
        <div class="mb-3">
            <label for="name" class="form-label">Enter New Snack Menu Name</label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" value="${snack.name }" placeholder="New Snack Menu Name" class="form-control" autofocus>
            </div>
        </div>
        
        <div class="mb-3">
            <label for="price" class="form-label">Enter Food Price</label>
            <div class="col-sm-9">
                <input type="text" id="price" name="price" value="${snack.price }" placeholder="Snack Price" class="form-control" autofocus>
            </div>
        </div>
        
        
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
	</div> <!-- ./container -->
        
        
        <c:import url="common/footer.html"></c:import>

</body>
</html>