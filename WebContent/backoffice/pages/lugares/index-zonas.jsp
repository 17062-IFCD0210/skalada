<%@page import="com.ipartek.formacion.skalada.controladores.LugaresController"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<div id="page-wrapper">

    
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Zonas disponibles</h1>
        </div>        <!-- /.col-lg-12 -->
    </div>    <!-- /.row -->
    
    <div class="row">
    
        <% 
			Mensaje msg = (Mensaje)request.getAttribute("msg");	
			if (msg != null){
				out.print("<div class='alert "+msg.getTipo()+" alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg.getTexto() +"</strong>");
				out.print("</div>");
			} 
		%>
		
		
		<%
			ArrayList<Zona> zonas = (ArrayList<Zona>)request.getAttribute("zonas");
			Zona zona = null;		
		%>
		
	</div> <!-- /.row -->

    <div class="row">  
   		<%
   			for (int i = 0 ; i < zonas.size() ; i++ ){
   				zona = zonas.get(i);   		
   		%>
   		<div class="col-md-6">   					
	        <div class="panel panel-warning">
	            <div class="panel-heading">
	                <div class="row">
	                    <div class="text-center">
	                        <i class="fa fa-map-o fa-5x"></i>
	                    </div>
	                    <div>
	                        <div class="text-right"></div>
	                        <div class="huge text-center"><%=zona.getNombre().toUpperCase() %></div>
	                    </div>
	                </div>
	            </div>
                <a href="<%=Constantes.CONTROLLER_LUGARES%>?accion=<%=LugaresController.MOSTRAR_SECTORES%>&id=<%=zona.getId()%>">
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