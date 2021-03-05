<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<title>Item Update</title>
	</head>
	<body>
		<h1>Item Update</h1>
		<div class="container">
			<div class="row">	
				<div class="col">
				
		<%
			String id = request.getParameter("itemID").toString();
			String code = request.getParameter("itemCode").toString();
			String name = request.getParameter("itemName").toString();
			String price = request.getParameter("itemPrice").toString();
			String desc = request.getParameter("itemDesc").toString();
		 %>
		<form method="post" action="update.jsp">
			
			Item code: <input name="itemCode" value="<%=code %>" type="text" class="form-control" ><br> 
			Item name: <input name="itemName" value="<%=name %>" type="text" class="form-control"><br> 
			Item price:<input name="itemPrice" value="<%=price %>" type="text" class="form-control"><br> 
			Item description: <input name="itemDesc" value="<%=desc %>" type="text" class="form-control"><br> 
			<input name="btnSubmit" type="submit" value="Update" class="btn btn-primary">
			<imput name ="itemID" type = "hidden" value="<%=id %>">
		</form>
		
		<div class="alert alert-success">
			<% 
				out.print(session.getAttribute("statusMsg"));
			%>
			<%
				if (request.getParameter("itemID") != null)
				{
					Item itemObj = new Item();
					String stsMsg = itemObj.updateItem(request.getParameter("itemID"),
						request.getParameter("itemCode"),
						request.getParameter("itemName"),
						request.getParameter("itemPrice"),
						request.getParameter("itemDesc"));
					session.setAttribute("statusMsg", stsMsg);
				}
			
			
				
			%>
		</div>
		</div>
		</div>
		</div>
		
		<br>
		
	</body>
</html>