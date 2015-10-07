<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@page errorPage="error.jsp"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav-user.jsp"></jsp:include>
	<% 
	Usuario usuario = null;
	//TODO controlar el usaurio
	usuario =(Usuario)session.getAttribute(Constantes.KEY_SESSION_USER);
	%>
<div id="page-wrapper">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">PANEL DE CONTROL DEL <%=usuario.getNombre() %></h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<ul>
			<li>
				<h2>SECTORES</h2> <!-- SECTORES -->
				<div class="row">

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-picture-o fa-4x"></i>
									</div>
									<div class="col-xs-9 text-right">


										<div class="huge"><%=sectoresPublicados%></div>
									</div>
								</div>
							</div>

							<div class="panel-footer">

								<a
									href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i
									class="fa fa-picture-o"></i> Sectores</a>

								<div class="clearfix"></div>
							</div>

						</div>
					</div>

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<span class="fa-stack fa-4x"> <i
											class="fa fa-picture-o  fa-stack-1x"></i> <i
											class="fa fa-ban fa-stack-2x text-danger"></i>
										</span>
									</div>
									<div class="col-xs-9 text-right">

										<div>SectoresPendientes</div>
										<div class="huge">XXXXX</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- fin sectores -->

			</li>
			<li>
				<h2>VIAS</h2>
				<div class="row">

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-picture-o fa-4x"></i>
									</div>
									<div class="col-xs-9 text-right">


										<div class="huge"><%=viasPublicadas%></div>
									</div>
								</div>
							</div>

							<div class="panel-footer">
								<a
									href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i
									class="fa fa-picture-o"></i> Vias</a>
								<div class="clearfix"></div>
							</div>

						</div>
					</div>

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<span class="fa-stack fa-4x"> <i
											class="fa fa-picture-o  fa-stack-1x"></i> <i
											class="fa fa-ban fa-stack-2x text-danger"></i>
										</span>
									</div>
									<div class="col-xs-9 text-right">

										<div>Vias Pendientes</div>
										<div class="huge">XXXXX</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- fin vias -->
			</li>
			<li>
				<h2>ZONAS</h2>
				<div class="row">

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-map-signs fa-fw fa-4x"></i>
									</div>
									<div class="col-xs-9 text-right">


										<div class="huge"><%=zonasPublicadas%></div>
									</div>
								</div>
							</div>
	
							<div class="panel-footer">

								<a
									href="<%=Constantes.CONTROLLER_ZONAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i
									class="fa fa-picture-o"></i> Zonas</a>

								<div class="clearfix"></div>
							</div>

						</div>
					</div>

					<div class="col-lg-4 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<span class="fa-stack fa-4x"> <i
											class="fa fa-map-signs fa-fw fa-1x"></i> <i
											class="fa fa-ban fa-stack-2x text-danger"></i>
										</span>
									</div>
									<div class="col-xs-9 text-right">

										<div>zonas pendientes</div>
										<div class="huge">XXXXX</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- fin Zonas -->
			</li>
			
		</ul>
	</div>
	<!-- /.row -->

</div>
<!-- /#page-wrapper -->


<jsp:include page="includes/foot.jsp"></jsp:include>


