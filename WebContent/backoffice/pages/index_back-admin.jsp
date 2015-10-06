<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@page errorPage="error.jsp"%>
<%
String sectoresPublicados="";
if(request.getAttribute("sectoresPublicados")!=null){
	sectoresPublicados=(String)request.getAttribute("sectoresPublicados");
}
String usuariosSinValidar="";
if(request.getAttribute("usuariosSinValidar")!=null){
	usuariosSinValidar=(String)(request.getAttribute("usuariosSinValidar"));
}
String usuariosConectados="";
if(request.getAttribute("usuariosConectados")!=null){
	usuariosConectados=(String)(request.getAttribute("usuariosConectados"));
}
%>


        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Panel de control</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
<!-- Usuarios conectados -->            
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=usuariosConectados%></div>
                                    <div>Usuarios conectados</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">Ver detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
<!-- Usuarios sin validar -->                
          	<div class="col-lg-6 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user-secret fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=usuariosSinValidar%></div>
                                    <div>Usuarios sin validar</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">Ver Detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
            </div>                
<!--  Sectores publicados -->            
            <div class="col-lg-6 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-map-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=sectoresPublicados%></div>
                                    <div>Sectores publicados</div>
                                </div>
                            </div>
                        </div>
                        <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>">
                            <div class="panel-footer">
                                <span class="pull-left">Ver detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
<!--  Sectores por validar -->            
            <div class="col-lg-6 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-map-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=sectoresPublicados%></div>
                                    <div>Sectores por validar</div>
                                </div>
                            </div>
                        </div>
                        <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>">
                            <div class="panel-footer">
                                <span class="pull-left">Ver detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>                
<!--  Zonas publicados -->            
            <div class="col-lg-6 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-globe fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">ZZZ</div>
                                    <div>Zonas publicadas</div>
                                </div>
                            </div>
                        </div>
                        <a href="<%=Constantes.CONTROLLER_ZONAS%>?accion=<%=Constantes.ACCION_LISTAR%>">
                            <div class="panel-footer">
                                <span class="pull-left">Ver detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
<!--  Zonas por validar -->            
            <div class="col-lg-6 col-md-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-globe fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">XXX</div>
                                    <div>Zonas por validar</div>
                                </div>
                            </div>
                        </div>
                        <a href="">
                            <div class="panel-footer">
                                <span class="pull-left">Ver detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>                                
<!--  Logs -->      
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-file-text-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">Logs</div>
                                </div>
                            </div>
                        </div>
                        <a href="<%=Constantes.VIEW_BACK_CONTENT_LOG%>">
                            <div class="panel-footer">
                                <span class="pull-left">Ver Detalles</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->


<jsp:include page="includes/foot.jsp"></jsp:include>
   

