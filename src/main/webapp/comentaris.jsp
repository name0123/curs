<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Curs Programació Segura</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	  $('#summernote').summernote();
});
</script>
</head>
<body>
	<div class="container">
		<h1>Comentaris ${param.autor}</h1>
		<c:forEach items="${comentaris}" var="c">
			<div class="well">
				<c:if test="${username==c.autor}" >
				<a class="btn btn-default pull-right" href="esborrar?id=${c.id}">Esborrar comentari</a>
				</c:if>
				<h3>${c.titol}</h3>
				<p>${c.comentari}</p>
				<i>Publicat per <a href="?autor=${c.autor}">${c.autor}</a> el dia ${c.data}</i>
			</div>
		</c:forEach>

		<a class="btn btn-lg btn-primary pull-right" href="comentar">Comentar</a>

	</div>
</body>
</html>