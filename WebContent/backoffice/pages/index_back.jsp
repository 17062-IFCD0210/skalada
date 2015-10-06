<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<!-- Recoger parametros para los usuarios y sectores -->

<%
			//Recoger parametros del controlador
			String sectoresPublicados="";
			if(request.getAttribute("sectoresPublicados")!=null){
				sectoresPublicados=(String)request.getAttribute("sectoresPublicados");
			}
			String usuariosSinValidar="";
			if(request.getAttribute("usuariosSinValidar")!=null){
				usuariosSinValidar=(String)(request.getAttribute("usuariosSinValidar"));
			}
			Usuario usuario=(Usuario)session.getAttribute(Constantes.KEY_SESSION_USER);
			String zonasPublicados="";
			if(request.getAttribute("zonasPublicados")!=null){
				sectoresPublicados=(String)request.getAttribute("zonasPublicados");
			}
			
			String viasPublicados="";
			if(request.getAttribute("viasPublicados")!=null){
				sectoresPublicados=(String)request.getAttribute("viasPublicados");
			}
%>   

 <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">CONTROL</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
            <ul>
            	<li><h2>USUARIOS</h2>
               <div class="row">
                
                <div class="col-lg-4 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                              
                                    <div class="huge"><%=usuariosSinValidar%></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                
                                <a href="<%=Constantes.CONTROLLER_USUARIOS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-users"></i> Usuarios Conectados</a>
                               
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
               
                <div class="col-lg-4 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user-times fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                 
                                    <div ><%=usuariosSinValidar%></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                
                                <a href="<%=Constantes.CONTROLLER_USUARIOS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-user-times"></i> Usuarios Sin Validar</a>
                               
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                </div><!-- usuarios -->
               </li>
               <li> <h2>SECTORES</h2>
                <!-- SECTORES -->
               <div class="row">
               
		                 <div class="col-lg-4 col-md-6">
		                    <div class="panel panel-green">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-picture-o fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                    
		                                    
		                                    <div class="huge" ><%=sectoresPublicados%></div>
		                                </div>
		                            </div>
		                        </div>
		                       
		                            <div class="panel-footer">
		                                
		                                <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o"></i> Sectores</a>
		                                
		                                <div class="clearfix"></div>
		                            </div>
		                       
		                    </div>
		                </div>
		                
		                 <div class="col-lg-4 col-md-6">
		                    <div class="panel panel-green">
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-3">
		                                    <i class="fa fa-picture-o fa-5x"></i>
		                                </div>
		                                <div class="col-xs-9 text-right">
		                                  	                                    
		                                     <div >SectoresPendientes</div>
		                                </div>
		                            </div>
		                        </div>
		                       
		                    </div>
		                </div>
                </div><!-- fin sectores -->
                
                </li>
                <li>  <h2>VIAS</h2>
                  <div class="row">
		               
				                 <div class="col-lg-4 col-md-6">
				                    <div class="panel panel-red">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-picture-o fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    
				                                    
				                                    <div class="huge" ><%=viasPublicados%></div>
				                                </div>
				                            </div>
				                        </div>
				                         
				                            <div class="panel-footer">		                                
				                                <a href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o"></i> Vias</a>
				                                <div class="clearfix"></div>
				                            </div>
				                      
				                    </div>
				                </div>
				                
				                 <div class="col-lg-4 col-md-6">
				                    <div class="panel panel-red">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-picture-o fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                   		                                    
				                                    <div >Vias Pendientes</div>
				                                </div>
				                            </div>
				                        </div>
				                       
				                    </div>
				                </div>
		          </div><!-- fin vias -->
		          </li>
		          <li> <h2>ZONAS</h2>
		          <div class="row">
			                
					                 <div class="col-lg-4 col-md-6">
					                    <div class="panel panel-yellow">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                                    <i class="fa fa-picture-o fa-5x"></i>
					                                </div>
					                                <div class="col-xs-9 text-right">
					                                 
					                                 
					                                    <div class="huge" ><%=zonasPublicados%></div>
					                                </div>
					                            </div>
					                        </div>
					                        
					                            <div class="panel-footer">
					                                
					                                <a href="<%=Constantes.CONTROLLER_ZONAS%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o"></i> Zonas</a>
					                                
					                                <div class="clearfix"></div>
					                            </div>
					                        
					                    </div>
					                </div>
					                
					                 <div class="col-lg-4 col-md-6">
					                    <div class="panel panel-yellow">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                                    <i class="fa fa-picture-o fa-5x"></i>
					                                </div>
					                                <div class="col-xs-9 text-right">
					                                   	                                    
					                                    <div>zonas pendientes</div>
					                                </div>
					                            </div>
					                        </div>
					                        
					                    </div>
					                </div>
                </div><!-- fin Zonas -->
                </li>
               <li> <h2>LOGS</h2>
                <div class="row">
				                
				                <div class="col-lg-4 col-md-6">
				                    <div class="panel panel-red">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-commenting fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div class="huge">LOGS</div>                                                                       
				                                </div>
				                            </div>
				                        </div>
				                        <a href="#">
				                            <div class="panel-footer">                              
				                                <a  href="<%=Constantes.VIEW_BACK_CONTENT_LOGS%>"><i class="fa fa-commenting"></i> Logs</a>                              
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
				                </div>
				                <div class="col-lg-4 col-md-6">
				                    <div class="panel panel-red">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-commenting fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div class="huge">ERROR</div>                                                                       
				                                </div>
				                            </div>
				                        </div>
				                        <a href="#">
				                            <div class="panel-footer">                              
				                                <a href="../backoffice/pages/error.jsp"><i class="fa fa-commenting"></i> Error</a>                              
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
				                </div>
                </div><!-- LOGS -->
                </li>
            </ul>
            </div>
            <!-- /.row -->
      
        </div>
        <!-- /#page-wrapper -->


<jsp:include page="includes/foot.jsp"></jsp:include>
   

