<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>

<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>

<div id="page-wrapper">
    
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Sectores
            	<!-- TODO llamar al Servlet, nunca a una JSP -->
            	<a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_NUEVO%>" class="btn btn-outline btn-success btn-lg">
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
    	<table id="tabla" class="display" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Nombre</th>
	                <th>Zona</th>
	                <% if(usuario.isAdmin()) { %>
	                <th>Autor</th>
	                <% } %>
	                <th>Validado</th>
	            </tr>
	        </thead> 
	        	 
	        <tbody>	           
	           <%
	           		// recoger el atributo "sectores" que nos llegara del Servlet con una coleccion de sectores(ArrayList<Sector>)
	           		ArrayList<Sector> sectores = (ArrayList<Sector>)request.getAttribute("sectores");
	           		
	           		Sector s = null;
	           		for(int i = 0 ; i < sectores.size() ; i++){
	           			s = sectores.get(i);
   	           %>
   	                <tr>
		                <td><%=s.getId()%></td>
		                <td>
		                	<a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_DETALLE%>&id=<%=s.getId()%>">
		                		<%=s.getNombre()%>
		                	</a>
		                </td>
		                <td><%=s.getZona().getNombre()%></td>
		                
		           	<% if(usuario.isAdmin()) { %>
		                <td>
		                	<a href="<%=Constantes.CONTROLLER_USUARIOS%>?accion=<%=Constantes.ACCION_DETALLE%>&id=<%=s.getUsuario().getId()%>">
		                		<%=s.getUsuario().getNombre()%>
		                	</a>
		                </td>
		            <% } %>
		                
		           	<% if(s.isValidado()){ %>
		            	<td><span class="label label-success">Validado</span></td>
		        	<% } else { %>
		           		<td><span class="label label-danger">Sin Validar</span></td>
		         	<% } %>
		            </tr>	            
	           <%
	           		} //end for
	           %>	            
           </tbody>
		</table>    
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            