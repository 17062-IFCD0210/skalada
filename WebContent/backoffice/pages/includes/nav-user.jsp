<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>                   
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
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
                            <a href="<%=Constantes.CONTROLLER_BACK_INDEX_BACK%>"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-map-signs fa-fw"></i> Vias</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_ZONAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-globe fa-fw"></i> Zonas</a>
                        </li>
                        <li>
                            <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-map-o fa-fw"></i> Sectores</a>
                        </li>
                        <li> 
                          <a href="#"><i class="fa fa-user fa-fw"></i> Perfil</a> 
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>