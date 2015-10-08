<%@page import="com.ipartek.formacion.skalada.controladores.LugaresController"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
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


	<%
		Zona zona = (Zona)request.getAttribute("zona");	
	%>
    
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Sectores disponibles en <%=zona.getNombre().toUpperCase()%></h1>
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
	
	</div> <!-- /.row -->

    <div class="row">  
   		<%
   			ArrayList<Sector> sectores = (ArrayList<Sector>)request.getAttribute("sectores");
   			Sector sector = null;	
   			for (int i = 0 ; i < sectores.size() ; i++ ){
   				sector = sectores.get(i);   		
   		%>
   		<div class="col-md-6">   					
	        <div class="panel panel-warning">
	            <div class="panel-heading">
	                <div class="row">
	                    <div class="text-center">
	                        <i class="fa fa-picture-o fa-5x"></i>
	                    </div>
	                    <div>
	                        <div class="text-right"></div>
	                        <div class="huge text-center"><%=sector.getNombre().toUpperCase() %></div>
	                    </div>
	                </div>
	            </div>
                <a href="<%=Constantes.CONTROLLER_LUGARES%>?accion=<%=LugaresController.MOSTRAR_VIAS%>&id=<%=sector.getId()%>">
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