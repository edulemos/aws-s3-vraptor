<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>AWS S3 VRAPTOR</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/css/jquery.toast.min.css"/>" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/css/jquery.loading.min.css"/>" rel="stylesheet" type="text/css" media="screen" />

<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/jquery.toast.min.js"/>"></script>
<script src="<c:url value="/js/jquery.loading.min.js"/>"></script>
<script src="<c:url value="/js/script.js"/>"></script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand loader" href="${linkTo[S3Controller].index()}">S3 CLIENT</a>
			</div>

			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">

		<div class="row">
			<%@ include file="buckets.jsp"%>

			<div class="col-lg-8">
				<div class="panel panel-default">
				