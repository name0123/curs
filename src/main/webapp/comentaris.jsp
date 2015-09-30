<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Comentaris</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Comentaris <c:out value="${param.autor}"/></h1>
		<c:forEach items="${comentaris}" var="c">
			<div class="well">
				<c:if test="${username==c.autor}" >
				<c:url value="/esborrar" var="url_esborrar">
				<c:param name="id" value="${c.id}"/>
				</c:url>
				<a class="btn btn-default pull-right" href="${url_esborrar}">Esborrar comentari</a>
				</c:if>
				<h3><c:out value="${c.titol}"/></h3>
				<p>${c.comentari}</p>
				<c:url value="/comentaris" var="url_autor">
				<c:param name="autor" value="${c.autor}"/>
				</c:url>
				<i>Publicat per <a href="${url_autor}"><c:out value="${c.autor}"></c:out></a> el dia <c:out value="${c.data}"/></i>
			</div>
		</c:forEach>
		<c:url value="/comentar" var="url_comentar"/>
		<a class="btn btn-lg btn-primary pull-right" href="${url_comentar}">Comentar</a>

	</div>
</body>
</html>