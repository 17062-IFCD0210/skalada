<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.controladores.LoginController"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>

 <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=Constantes.ROOT_APP + Constantes.VIEW_BACK_INDEX%>"> Backoffice </a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right panel-menu">
            
            
            <%
				int user_rol = 0;
				Usuario usuario = (Usuario) session.getAttribute(Constantes.KEY_SESSION_USER);
				if (usuario != null){
					user_rol = usuario.getRol().getId();
				}
			%>

                <!-- Perfil del usuario -->
                <li class="dropdown">
					<a href="#" class="dropdown-toggle account" data-toggle="dropdown">
						<div class="avatar">
							<span class="img-circle"><i class="fa fa-user fa-3x"></i></span>
<!-- 							<img src="../uploads/avatar_admin.png" class="img-circle" alt="avatar"> -->
						</div>
						<i class="fa fa-angle-down pull-right"></i>
						<div class="user-mini pull-right">
							<span class="welcome">${sessionScope.ss_user.nombre}</span>
							<span>${sessionScope.ss_user.rol.nombre}</span>
						</div>
					</a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="<%=Constantes.CONTROLLER_LOGOUT%>"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        
                        <li>
                        	<a href="<%=Constantes.CONTROLLER_HOME%>"><i class="fa fa-globe fa-fw"></i> Web Publica</a>
                        </li>                        	
                        <li>
                            <a href="home"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        
                        <%	if(user_rol == Constantes.ROLE_ADMIN_ID){	%>
							<jsp:include page="nav-admin.jsp"></jsp:include>
						<%	} else { %>
							<jsp:include page="nav-user.jsp"></jsp:include>
						<%	} %>


                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
