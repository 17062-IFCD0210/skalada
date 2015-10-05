<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>


<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<div id="page-wrapper">

    
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Grados
            	<!-- TODO llamar al Servlet, nunca a una JSP -->
            	<a href="<%=Constantes.CONTROLLER_GRADOS%>?accion=<%=Constantes.ACCION_NUEVO%>" class="btn btn-outline btn-success btn-lg">
            		<i class="fa fa-plus"></i> Nuevo
            	</a>
           	</h1>
        </div>        <!-- /.col-lg-12 -->
    </div>    <!-- /.row -->
    
    <div class="row">
        <% 
			String msg = (String)request.getAttribute("msg-success");	
			if (msg != null){
				out.print("<div class='alert alert-success alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg +"</strong>");
				out.print("</div>");
			} 
		%>
		
		<%	
			msg = (String)request.getAttribute("msg-warning");	
			if (msg != null){
				out.print("<div class='alert alert-warning alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg +"</strong>");
				out.print("</div>");
			} 
		%>
		
		<%	
			msg = (String)request.getAttribute("msg-danger");	
			if (msg != null){
				out.print("<div class='alert alert-danger alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg +"</strong>");
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
	                <th>Nombre</th>
	                <th>Descripción</th>
	            </tr>
	        </thead> 
	        	 
	        <tbody>	   
	        	<!-- for each -->
	        	<c:forEach var="grado" items="${requestScope.grados}">
	        	<tr>
	        		<td>${grado.id}</td>
	        		<td>
		                	<a href="<%=Constantes.CONTROLLER_GRADOS%>?accion=<%=Constantes.ACCION_DETALLE%>&id=${grado.id}">
		                		${grado.nombre}
		                	</a>
		            </td>
	        		<td>${grado.descripcion}</td>
	        	
	        	</tr>
	        	</c:forEach>
	        
	                
	
           </tbody>
		</table>    
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            