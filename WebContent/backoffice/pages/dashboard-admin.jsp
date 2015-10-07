<%@page import="com.ipartek.formacion.skalada.listener.ListenerSession"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Panel de control</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>	<!-- /.row -->

<div class="row">
	<h2>Usuarios</h2>	
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x"> 
							<i class="fa fa-users fa-stack-1x"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge"><%=ListenerSession.session_users.size()%></div>
						<div class="huge">Usuarios conectados</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x"> 
							<i class="fa fa-user fa-stack-1x"></i> 
							<i class="fa fa-ban fa-stack-2x text-danger"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge"><%=request.getAttribute("num_user_invalid")%></div>
						<div class="huge">Usuarios sin validar</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>	
</div>	<!-- /.row -->

<div class="row">
	<h2>Zonas</h2>	
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x">
							<i class="fa fa-map-o fa-stack-1x"></i> 							
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge"><%=request.getAttribute("num_zonas")%></div>
						<div class="huge">Zonas<br>publicadas</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x">
							<i class="fa fa-map-o fa-stack-1x"></i> 
							<i class="fa fa-ban fa-stack-2x text-danger"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">XXX</div>
						<div class="huge">Zonas no publicadas</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>	<!-- /.row -->

<div class="row">
	<h2>Sectores</h2>	
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x">
							<i class="fa fa-picture-o fa-stack-1x"></i> 
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge"><%=request.getAttribute("num_sectores")%></div>
						<div class="huge">Sectores<br>publicados</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x">
							<i class="fa fa-picture-o fa-stack-1x"></i> 
							<i class="fa fa-ban fa-stack-2x text-danger"></i>							
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">XXX</div>
						<div class="huge">Sectores NO publicados</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>	<!-- /.row -->

<div class="row">
	<h2>Vias</h2>
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x"> <i
							class="fa fa-map-signs fa-stack-1x"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge"><%=request.getAttribute("num_vias")%></div>
						<div class="huge">Vias<br>publicadas</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-green">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x">
							<i class="fa fa-map-signs fa-stack-1x"></i> 
							<i class="fa fa-ban fa-stack-2x text-danger"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">XXX</div>
						<div class="huge">Vias NO publicadas</div>
					</div>
				</div>
			</div>
			<a href="#">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>	<!-- /.row -->

<div class="row">
	<h2>Logs</h2>	
	<div class="col-md-offset-3 col-md-6">
		<div class="panel panel-yellow">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-3">
						<span class="fa-stack fa-5x"> <i
							class="fa fa-file-text-o fa-stack-1x"></i>
						</span>
					</div>
					<div class="col-xs-9 text-right">
						<div class="huge">.</div>
						<div class="huge">Logs</div>
					</div>
				</div>
			</div>
			<a href="<%=Constantes.VIEW_BACK_CONTENT_LOGS%>">
				<div class="panel-footer">
					<span class="pull-left">View Details</span> <span
						class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>	<!-- /.row -->