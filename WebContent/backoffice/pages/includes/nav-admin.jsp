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
                                                  
                    </ul>