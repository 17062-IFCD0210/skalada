<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.TipoEscalada"%>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page pageEncoding = "utf-8" %>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//Recoger paramtero "via" de la Clase Via
	Via via = (Via)request.getAttribute("via");
	ArrayList<Grado> grados = (ArrayList<Grado>) request.getAttribute("lista_grados");
	ArrayList<TipoEscalada> tipos = (ArrayList<TipoEscalada>) request.getAttribute("lista_tipos");
	ArrayList<Sector> sectores = (ArrayList<Sector>) request.getAttribute("lista_sectores");

%>
       

        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><%=request.getAttribute("titulo") %></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
	           <!-- Formulario -->
	           <form role="form" action ="<%=Constantes.CONTROLLER_VIAS%>" method="post">
		           <div class="form-group">
		           	<!-- Mostramos el input text, pero se submita el hidden -->
	                   <label for="id">ID</label>
	                   <input type="hidden" name="id" value="<%=via.getId()%>">
	                   <input type="text" class="form-control" value="<%=via.getId()%>" disabled>
	               </div>
	               <div class="form-group">
	                   <label for="nombre">Nombre</label>
	                   <input class="form-control" name="nombre" value="<%=via.getNombre()%>" required>
	               </div>
	               <div class="form-group">
	                   <label for="grado">Dificultad</label>
						<select class="form-control" name="grado">
							<%
	                		   	for(int i = 0; i<grados.size(); i++) {
	                		   		if(grados.get(i).getId() == via.getGrado().getId()) {
	                		 %>
	                		 <option selected value="<%=grados.get(i).getId()%>"><%=grados.get(i).getNombre() %></option>
	                		 <%
	                		   		} else {
	                		 %>
	                		 <option value="<%=grados.get(i).getId()%>"><%=grados.get(i).getNombre() %></option>  			
	            	         <%
	                		   		} //else	
	                		   	} // For
	                 	     %>					
						</select>
	               </div>
	               <div class="form-group">
	                   <label for="long">Longitud</label>
	                   <input class="form-control" type="number" name="long" value="<%=via.getLongitud()%>" required>
	               </div>
	               <div class="form-group">
	                   <label for="desc">Descripcion</label>
	                   <textarea class="form-control" name="desc" required><%=via.getDescripcion()%></textarea>
	               </div>
	               <div class="form-group">
	                   <label for="tipo_esc">Tipo Escalada</label>
						<select class="form-control" name="tipo_esc">
							<%
	                		   	for(int i = 0; i<tipos.size(); i++) {
	                		   		if(tipos.get(i).getId() == via.getTipoEscalada().getId()) {
	                		 %>
	                		 <option selected value="<%=tipos.get(i).getId()%>"><%=tipos.get(i).getNombre() %></option>
	                		 <%
	                		   		} else {
	                		 %>
	                		 <option value="<%=tipos.get(i).getId()%>"><%=tipos.get(i).getNombre() %></option>  			
	            	         <%
	                		   		} //else	
	                		   	} // For
	                 	     %>					
						</select>
	               </div>
	               <div class="form-group">
	                   <label for="sector">Sector</label>
						<select class="form-control" name="sector">
							<%
	                		   	for(int i = 0; i<sectores.size(); i++) {
	                		   		if(sectores.get(i).getId() == via.getSector().getId()) {
	                		 %>
	                		 <option selected value="<%=sectores.get(i).getId()%>"><%=sectores.get(i).getNombre() %></option>
	                		 <%
	                		   		} else {
	                		 %>
	                		 <option value="<%=sectores.get(i).getId()%>"><%=sectores.get(i).getNombre() %></option>  			
	            	         <%
	                		   		} //else	
	                		   	} // For
	                 	     %>					
						</select>
	               </div>
	               <!-- Botonera -->
	               <div class="form-group">
	               		<% if(via.getId() != -1) { %>
		               		<input type="submit" value="Modificar" class="btn btn-outline btn-primary">
		                   	<input type="button" value="Eliminar" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#myModal">

				<!-- Ventana Modal -->
				<div class="modal fade col-md-6 col-md-offset-3" id="myModal"
					role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title text-center text-danger">
									<i class="fa fa-exclamation-triangle"></i> ELIMINAR VIA:
									<%=via.getNombre().toUpperCase() %></h2>
							</div>
							<div class="modal-body">
								<p>Estas seguro de que desea eliminar la siguiente via:</p>
								<div class="row">
									<div class="form-group col-md-6">
										<label for="id">ID</label> 
										<input type="text" name="id" class="form-control" value="<%=via.getId()%>" disabled>
									</div>
									<div class="form-group col-md-6">
										<label for="nombre">Nombre</label> 
										<input type="text" name="nombre" class="form-control" value="<%=via.getNombre()%>" disabled>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label for="grado">Dificultad</label> 
										<input type="text" name="grado" class="form-control" value="<%=via.getGrado().getNombre()%>" disabled>
									</div>
									<div class="form-group col-md-6">
										<label for="longitud">Longitud</label> 
										<input type="text" name="longitud" class="form-control" value="<%=via.getLongitud()%>" disabled>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-12">
										<label for="descripcion">Descripcion</label>
										<textarea class="form-control" rows="3" name="descripcion" disabled><%=via.getDescripcion()%></textarea>
									</div>
								</div>
								<div class="row checkbox">
									<div class="form-group col-md-12">
										<label> <input type="checkbox" id="check_eliminar">S&iacute;,
											estoy seguro. Deseo eliminar la Via seleccionada.
										</label>
										<!-- Habilitar eliminacion mediante checkbox -->
										<script>
											document.getElementById('check_eliminar').onclick = function () {
												document.getElementById('boton_eliminar').classList.toggle("disabled");				
											}	
										</script>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=via.getId()%>" id="boton_eliminar" class="btn btn-danger disabled">Eliminar</a>
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
						<!-- END Modal content-->
					</div>
				</div>
				<!-- END Ventana Modal -->
				
				<%} else { %>
	                   		<input type="submit" value="Crear" class="btn btn-outline btn-primary">
		                   	<input type="reset" value="Limpiar" class="btn btn-outline btn-danger">
		                <%} %>
	               </div>
	           </form>
            </div>
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>