<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<link rel="shortcut icon" href="img/favicon.html">

<title>404</title>

<!-- Bootstrap core CSS -->
<link href="<%=basePath %>resources/manage/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>resources/manage/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>resources/manage/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="<%=basePath %>resources/manage/css/style.css" rel="stylesheet">
<link href="<%=basePath %>resources/manage/css/style-responsive.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="body-404">

	<div class="container">

		<section class="error-wrapper"> <i class="icon-404"></i>
		<h1>404</h1>
		<h2>page not found</h2>
		<p class="page-404">
			Something went wrong or that page doesn’t exist yet. <a
				href="<%=basePath %>index">Return Home</a>
		</p>
		</section>

	</div>


</body>
</html>
