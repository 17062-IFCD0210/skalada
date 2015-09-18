<!DOCTYPE html>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page pageEncoding = "utf-8" %>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Modern Business - Start Bootstrap Template</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/modern-business.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=Constantes.ROOT_APP%>">Skalada</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="about.html">About</a></li>
					<li><a href="services.html">Services</a></li>
					<li><a href="contact.html">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Portfolio <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="portfolio-1-col.html">1 Column Portfolio</a></li>
							<li><a href="portfolio-2-col.html">2 Column Portfolio</a></li>
							<li><a href="portfolio-3-col.html">3 Column Portfolio</a></li>
							<li><a href="portfolio-4-col.html">4 Column Portfolio</a></li>
							<li><a href="portfolio-item.html">Single Portfolio Item</a>
							</li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Blog <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="blog-home-1.html">Blog Home 1</a></li>
							<li><a href="blog-home-2.html">Blog Home 2</a></li>
							<li><a href="blog-post.html">Blog Post</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Other Pages <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="full-width.html">Full Width Page</a></li>
							<li><a href="sidebar.html">Sidebar Page</a></li>
							<li><a href="faq.html">FAQ</a></li>
							<li><a href="404.html">404</a></li>
							<li><a href="pricing.html">Pricing Table</a></li>
						</ul></li>

					<!-- enlace backoffice -->
					<li><a href="backoffice/pages/login.jsp">Login</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					<%=request.getAttribute("titulo")%>
				</h1>
			</div>
		</div>
		<!-- /.row -->

		<!-- Portfolio Item Row -->
		<div class="row">

			<div class="col-lg-12">
				<img class="img-responsive img-portfolio img-hover"
 					src="<%=request.getAttribute("imagen")%>" alt="">
			</div>
		</div>
		<!-- /.row -->

		<!-- Related Projects Row -->
		<div class="row">

			<div class="col-lg-12">
				<h3 class="page-header">Descripcion</h3>
				<p><%=request.getAttribute("desc")%></p>
			</div>

		</div>
		<!-- /.row -->

		<hr>

		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">Te podria interesar...</h3>
			</div>
 			<% 
               	ArrayList<Sector> sector = (ArrayList<Sector>)request.getAttribute("cuatro_vias");
            	if(sector == null) {
            		sector = new ArrayList<Sector>();
            	}
            	Sector s = null;
            	
            	for(int i=0; i<sector.size();i++) {
            		s = sector.get(i); 
             %> 
             <div class="col-md-3 col-sm-3">
             	<figure> 
            		<a href="<%=Constantes.CONTROLLER_INFO_SECTOR%>?id=<%=s.getId()%>">
             			<img class="img-responsive img-portfolio img-hover" src="<%=Constantes.IMG_WEP_PATH + s.getImagen()%>" alt="">
            		</a>
            		<figcaption><a href="<%=Constantes.CONTROLLER_INFO_SECTOR%>?id=<%=s.getId()%>"><%=s.getNombre()%></a></figcaption>
             	</figure> 
            </div> 
 			<%	} //Cerramos el For  %>
		</div>
		
		
		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Your Website 2014</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
