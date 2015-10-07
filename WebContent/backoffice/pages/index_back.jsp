<%@page import="com.ipartek.formacion.skalada.modelo.ModeloVia"%>
<%@page import="com.ipartek.formacion.skalada.modelo.ModeloZona"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.modelo.ModeloSector"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page pageEncoding="UTF-8"%> 

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>



        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <a href="#">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">5</div>
                                    <div>Usuarios conectados</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
                <a href="#">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user-times fa-3x"></i>
                                    
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge" value="">2</div>
                                    <div>Usuarios sin validar</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
                <a href="<%=Constantes.VIEW_BACK_SECTORES_INDEX%>">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-map fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=ModeloSector.totalSectores()%></div>
                                    <div>Sectores</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
                <a href="<%=Constantes.VIEW_BACK_ZONAS_INDEX%>">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-map fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=ModeloZona.totalZonas()%></div>
                                    <div>Zonas</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
                <a href="<%=Constantes.VIEW_BACK_VIAS_INDEX%>">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-map fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"><%=ModeloVia.totalVias()%></div>
                                    <div>Vias</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
                <a href="<%=Constantes.VIEW_BACK_CONTENT_LOGS%>">
                <div class="col-lg-6 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-file-text-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge"></div>
                                    <div>Logs</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </a>
                
           

<jsp:include page="includes/foot.jsp"></jsp:include>