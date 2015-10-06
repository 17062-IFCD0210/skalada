<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.controladores.LoginController"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%
	Usuario usuario = null;
	//TODO controlar el usaurio
	usuario =(Usuario)session.getAttribute(Constantes.KEY_SESSION_USER);
	int rol = usuario.getRol().getId();

%>



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

            <ul class="nav navbar-top-links navbar-right">
                
                <!-- Perfil del usuario -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    	                   	                    	
                    	${sessionScope.ss_user.nombre}(${sessionScope.ss_user.rol.nombre})
                    	
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
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
                
                <%if(rol==Constantes.ROLE_ID_ADMIN){ %>
                	<jsp:include page="nav-admin.jsp"></jsp:include>
                
                <%}else{ %>
                	<jsp:include page="nav-user.jsp"></jsp:include>
                
                <%} %>
                   <%-- <%--  <ul class="nav" id="side-menu">
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
                            <a href="<%=Constantes.VIEW_BACK_INDEX%>"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-map-signs fa-fw"></i> Vias</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_GRADOS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-graduation-cap fa-fw"></i> Grados</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_TIPO_ESCALADA%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-bar-chart-o fa-fw"></i> Tipos Escalada</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_ZONAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o fa-fw"></i> Zonas</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o fa-fw"></i> Sectores</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_USUARIOS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o fa-fw"></i> Usuarios</a>
                        </li> 
                        <li> 
                          <a href="<%=Constantes.CONTROLLER_ROLES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o fa-fw"></i> Roles</a> 
                        </li>
                       
                         <li> 
                          <a href="../backoffice/pages/error.jsp"><i class="fa fa-picture-o fa-fw"></i>Error</a> 
                        </li>
                                                  
                    </ul> --%> 
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
