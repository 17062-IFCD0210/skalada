<%@page import="com.ipartek.formacion.skalada.bean.Rol"%>
<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.bean.Oferta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<div id="page-wrapper">

    
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Ofertas
            	<!-- TODO llamar al Servlet, nunca a una JSP -->
            	<a href="<%=Constantes.CONTROLLER_OFERTAS%>?accion=<%=Constantes.ACCION_NUEVO%>" class="btn btn-outline btn-success btn-lg">
            		<i class="fa fa-plus"></i> Nuevo
            	</a>
           	</h1>
        </div>        <!-- /.col-lg-12 -->
    </div>    <!-- /.row -->
    
    <div class="row">
        <% 
            Mensaje msg = (Mensaje)request.getAttribute("msg");	
			if (msg != null){
				out.print("<div class='alert alert-"+ msg.getTipo() +" alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg.getTexto() +"</strong>");
				out.print("</div>");
			} 
		%>
	</div> <!-- /.row -->

    <div class="row">
    
    <!-- tabla -->
    	<table id="tabla" class="display"  >
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Titulo</th>
	                <th>Descripcion</th>
	                <th>Precio</th>
	                <th>Fecha_alta</th>
	                <th>Fecha_baja</th>
	            </tr>
	        </thead> 
	        	 
	        <tbody>	           
	           <%
	           		// recoger el atributo "ofertas" que nos llegara del Servlet con una coleccion de ofertas(ArrayList<Oferta>)
	           		ArrayList<Oferta> ofertas= (ArrayList<Oferta>)request.getAttribute("ofertas");
	   				if ( ofertas == null ){
	   					ofertas = new ArrayList<Oferta>();
	   				}
	           		Oferta r = null;
	           		for(int i = 0 ; i < ofertas.size() ; i++){
	           			r = ofertas.get(i);
   	           %>
   	                <tr>
		                <td><%=r.getId()%></td>
		                <td>
		                	<a href="<%=Constantes.CONTROLLER_OFERTAS%>?accion=<%=Constantes.ACCION_DETALLE%>&id=<%=r.getId()%>">
		                		<%=r.getTitulo()%>
		                	</a>
		                </td>
		                <td><%=r.getDescripcion()%></td>
		                <td><%=r.getPrecio()%></td>
		                <td><%=r.getFecha_alta()%></td>
		                <td><%=r.getFecha_baja()%></td>
		            </tr>	            
	           <%
	           		} //end for
	           %>	            
           </tbody>
		</table>    
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            