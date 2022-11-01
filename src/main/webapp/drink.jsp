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
     <button> <a href="add-new-drink.jsp">Add New Menu</a> </button>
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
                        <c:forEach var="drink" items="${drinkList }">
                        	<c:url var="updateDrink" value="drink">
                        		<c:param name="mode" value="LOAD"></c:param>
                        		<c:param name="id" value="${drink.id }"></c:param>
                        	</c:url>
                        	
                        	<c:url var="deleteDrink" value="drink">
                        		<c:param name="mode" value="DELETE"></c:param>
                        		<c:param name="id" value="${drink.id }"></c:param>
                        	</c:url>
                        
                        <tr>
                            <td> <c:out value="${drink.id }"></c:out> </td>
                            <td> <c:out value="${drink.name }"></c:out> </td>
                            <td> <c:out value="${drink.price }"></c:out> </td>
                            <c:if test="${fn:contains(user.role,'admin') }">
                            <td> 
                            	<a href="${updateDrink }" class="btn btn-primary">Edit</a>
                            	<a href="${deleteDrink }" id="delete" class="btn btn-danger" onclick="confirm('Are you sure to delete this drink menu?');">Delete</a>
                            	
                            </td>
                            </c:if>
                            <c:if test="${fn:contains(user.role,'user') }">
                            	<td> <a class="btn btn-primary" onclick="confirm('Are you sure to order?');">Order</a> </td>
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