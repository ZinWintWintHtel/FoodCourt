<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<c:import url="common/header.html"></c:import>
    <title>Food Court</title>
<body>
	<div class="container"></div>
        
    <!-- Responsive navbar-->
    <c:import url="common/nav-bar.jsp"></c:import>
     <c:if test="${fn:contains(user.role,'admin') }">
     <button> <a href="add-new-snack.jsp">Add New Menu</a> </button>
      </c:if>  
    <div class="container mt-5">
   
                <table id="foodCourt" class="table table-striped">
                
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="snack" items="${snackList }">
                        	<c:url var="updateSnack" value="snack">
                        		<c:param name="mode" value="LOAD"></c:param>
                        		<c:param name="id" value="${snack.id }"></c:param>
                        	</c:url>
                        	
                        	<c:url var="deleteSnack" value="snack">
                        		<c:param name="mode" value="DELETE"></c:param>
                        		<c:param name="id" value="${snack.id }"></c:param>
                        	</c:url>
                        
                        <tr>
                            <td> <c:out value="${snack.id }"></c:out> </td>
                            <td> <c:out value="${snack.name }"></c:out> </td>
                            <td> <c:out value="${snack.price }"></c:out> </td>
                            <c:if test="${fn:contains(user.role,'admin') }">
                            <td> 
                            	<a href="${updateSnack }" class="btn btn-primary">Edit</a>
                            	<a href="${deleteSnack }" id="delete" class="btn btn-danger" onclick="confirm('Are you sure to delete this snack menu?');">Delete</a>
                            	
                            </td>
                            </c:if>
                            <c:if test="${fn:contains(user.role,'user') }">
                            <td>
                            <a class="btn btn-primary" onclick="confirm('Are you sure to order?');">Order</a>
                            </td>
                            </c:if>
                        </tr>
                    	</c:forEach>
                    </tbody>
                    <!-- <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Office</th>
                            <th>Age</th>
                            <th>Start date</th>
                            <th>Salary</th>
                        </tr>
                    </tfoot> -->
                </table>
           
        </div>
        
        <c:import url="common/footer.html"></c:import>    
        
</body>
</html>