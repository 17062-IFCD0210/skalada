<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page pageEncoding = "utf-8" %>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

       

        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Sectores
                    	<!-- TODO llamar al Servlet, no al JSP -->
	                    <a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_NUEVO%>" type="button" class="btn btn-outline btn-success">
	                    	<i class="fa fa-plus"></i> Nuevo
	                    </a>
                    </h1>
                    <%
                    	String msg_elim = (String)request.getAttribute("msg_elim");
                     	String msg_new = (String)request.getAttribute("msg_new");
                     	String msg_mod = (String)request.getAttribute("msg_mod");
                    	
                    	if(msg_elim != null) {
                    		out.print("<div class='alert alert-danger alert-dismissible' role='alert'>");
							out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
							out.print("<span aria-hidden='true'>&times;</span>");
							out.print("</button>");
							out.print("<strong>" + msg_elim + "</strong>");
							out.print("</div>");
                    	}
                    	
                    	if(msg_new != null) {
                    		out.print("<div class='alert alert-success alert-dismissible' role='alert'>");
							out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
							out.print("<span aria-hidden='true'>&times;</span>");
							out.print("</button>");
							out.print("<strong>" + msg_new + "</strong>");
							out.print("</div>");
                    	}
                    	
                    	if(msg_mod != null) {
                    		out.print("<div class='alert alert-info alert-dismissible' role='alert'>");
							out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
							out.print("<span aria-hidden='true'>&times;</span>");
							out.print("</button>");
							out.print("<strong>" + msg_mod + "</strong>");
							out.print("</div>");
                    	}
                    
                    %>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">

		<table id="tabla" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Zona</th>
					<th>URL Imagen</th>
				</tr>
			</thead>

			<tbody>
			<%
				//Recoger el atributo "grados" que llegara del servlet con una coleccion de grados
				ArrayList<Sector> sectores = (ArrayList<Sector>)request.getAttribute("sectores");
				
				Sector s = null;
				for(int i=0; i<sectores.size(); i++) {
					s = sectores.get(i);
			%>
				<tr>
					<td><%=s.getId()%></td>
					<td>
						<a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_DETALLE%>&id=<%=s.getId()%>">
							<%=s.getNombre() %>
						</a>
					</td>
					<td><%=s.getZona().getNombre()%></td>
					<td><%=s.getImagen()%></td>
				</tr>
			<%	} //Cerramos el For  %>	
			</tbody>
		</table>
	</div>     
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>