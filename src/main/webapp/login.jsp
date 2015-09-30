<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger">Username o password incorrecte</div>
		</c:if>
		<form action="j_security_check" method="POST" class="form-signin">
			<div class="form-group">
				<label for="j_username">Username</label>
				<input type="text" name="j_username" class="form-control">
			</div>
			<div class="form-group">
				<label for="j_password">Password</label>
				<input type="password" name="j_password" class="form-control">
			</div>
			<button class="btn btn-lg btn-primary pull-right" type="submit">Login</button>
		</form>
	</div>
</body>
</html>