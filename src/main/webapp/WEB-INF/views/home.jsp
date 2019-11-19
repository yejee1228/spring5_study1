<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="${js}app.js"></script>
	<script src="${js}router.js"></script>
</head>
<body>
	<div id = "wrapper">
	<script>
	app.onCreate('<%=application.getContextPath()%>');
	</script>
	</div>
</body>
</html>