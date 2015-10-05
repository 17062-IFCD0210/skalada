<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<!-- Recoger parametros para los usuarios y sectores -->
<%
	String sectoresPublicados="";
if(request.getAttribute("sectoresPublicados")!=null){
	sectoresPublicados=(String)request.getAttribute("sectoresPublicados");
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
                <div class="col-lg-5 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">12</div>
                                    <div>Usuarios conectados</div>
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
               
                <div class="col-lg-5 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user-times fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">12</div>
                                    <div>Usuarios sin validar</div>
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
               
                
                 <div class="col-lg-5 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-picture-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">X</div>
                                    
                                    <div><%=sectoresPublicados%></div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                
                                <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_LISTAR%>"><i class="fa fa-picture-o"></i> Sectores</a>
                                
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-5 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-commenting fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">¿???</div>                                                                       
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
            </div>
            <!-- /.row -->
      
        </div>
        <!-- /#page-wrapper -->


<jsp:include page="includes/foot.jsp"></jsp:include>
   

