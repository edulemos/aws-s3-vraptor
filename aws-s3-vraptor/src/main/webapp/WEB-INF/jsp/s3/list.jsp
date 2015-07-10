<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>AWS S3 VRAPTOR</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>AWS S3</h1>
			<p>Aplicação de Exemplo Amazon S3 com Vraptor 4</p>
		</div>

		<div class="row">
			<div class="col-lg-12 text-center">

				<br>

				<div class="panel panel-default">
					<div class="panel-heading">BUCKETS</div>
					<div class="panel-body">
						<c:forEach items="${bucketsList}" var="bucket">
							<li class="list-group-item text-left"><a href="${linkTo[S3Controller].bucketListFiles(bucket.name)}">${bucket.name}</a></li>
						</c:forEach>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">DETALHES</div>
					<div class="panel-body">
						<c:forEach items="${bucketFiles}" var="file">
							<li class="list-group-item text-left">${file.key}</li>
						</c:forEach>
					</div>
				</div>

			</div>
		</div>
</body>
</html>
