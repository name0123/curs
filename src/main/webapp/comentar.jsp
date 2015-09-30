<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Comentar</title>
<!--  carreguem jQuery, bootstrap i summernote rich text editor -->
<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
<link href="summernote.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.6.16/summernote.min.js"></script>
<link href=https://cdnjs.cloudflare.com/ajax/libs/summernote/0.6.16/summernote.css rel="stylesheet">
<script>
$(document).ready(function() {
	  $('#summernote').summernote();
});
</script>
</head>
<body>
	<div class="container">
	<h1>Afegir comentari en nom de ${username}...</h1>
	<form method="POST">
		<div class="form-group">
			<label for="titol">Titol</label>
			<input type="text" name="titol" class="form-control"></input>
			<label for="comentari">Comentari</label>
			<textarea id="summernote" name="comentari" class="form-control"></textarea>
		</div>
		<button class="btn btn-lg btn-primary pull-right" type="submit">Comentar</button>
	</form>
	</div>
</body>
</html>