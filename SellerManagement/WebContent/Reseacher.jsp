<%@page import="com.Reseacher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Reseacher.js"></script>

<meta charset="ISO-8859-1">
<title>Research Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Research Management</h1>

	<form id="formItem" name="formItem">
 
		 Research Name:
		<input id="ResearchName" name="ResearchName" type="text" class="form-control form-control-sm"><br> 
		 Research Description:
		<input id="ResearchDiscription" name="ResearchDiscription" type="text" class="form-control form-control-sm"><br>
		 Researcher Name:
		<input id="ResearcherName" name="ResearcherName" type="text" class="form-control form-control-sm"><br>		
		 Research Price(Rs):
		<input id="ResearchPrice" name="ResearchPrice" type="text" class="form-control form-control-sm"><br>
		 
				
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	Reseacher ResearchObj = new Reseacher(); 
		 out.print(ResearchObj.readResearch());
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>