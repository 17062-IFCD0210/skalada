<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page import="com.ipartek.formacion.skalada.controladores.LugaresController"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<div id="page-wrapper">

	<%
		Sector sector = (Sector) request.getAttribute("sector");
		Zona zona = (Zona) request.getAttribute("zona");
	%>
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Vias disponibles en "<%=sector.getNombre()%>" (<%=zona.getNombre().toUpperCase() %>)</h1>
        </div>        <!-- /.col-lg-12 -->
    </div>    <!-- /.row -->

    <div class="row">  
   		<%
   			ArrayList<Via> vias = (ArrayList<Via>)request.getAttribute("vias");
   			Via via = null;	
   			for (int i = 0 ; i < vias.size() ; i++ ){
   				via = vias.get(i);   		
   		%>
   		<div class="col-md-6">   					
	        <div class="panel panel-warning">
	            <div class="panel-heading">
	                <div class="row">
	                    <div class="text-center">
	                        <i class="fa fa-map-signs fa-5x"></i>
	                    </div>
	                    <div>
	                        <div class="text-right"></div>
	                        <div class="huge text-center"><%=via.getNombre().toUpperCase() %></div>
	                    </div>
	                </div>
	            </div>
                <a href="<%=Constantes.CONTROLLER_LUGARES%>?accion=<%=LugaresController.MOSTRAR_DETALLE%>&id=<%=via.getId()%>">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <%
   			}
        %>
	</div>
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            