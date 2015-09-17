<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page pageEncoding = "utf-8" %>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//Recoger atributos "grado" de la Clase Grado
	Sector s = (Sector)request.getAttribute("sector");
	ArrayList<Zona> zonas = (ArrayList<Zona>) request.getAttribute("lista_zonas");
	

%>
       

        <div id="page-wrapper">
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
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><%=request.getAttribute("titulo") %></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
	           <!-- Formulario -->
	           <form role="form" action ="<%=Constantes.CONTROLLER_SECTORES%>" method="post" enctype="multipart/form-data">
		           <div class="form-group col-lg-1">
		           	<!-- Mostramos el input text, pero se submita el hidden -->
	                   <label for="id">ID</label>
	                   <input type="hidden" name="id" value="<%=s.getId()%>">
	                   <input type="text" class="form-control" value="<%=s.getId()%>" disabled>
	               </div>
	               <div class="form-group col-lg-11">
	                   <label for="nombre">Nombre</label>
	                   <input class="form-control" name="nombre" value="<%=s.getNombre()%>" required>
	               </div>
	              <div class="form-group col-lg-12">
	                   <label for="zona">Zona</label>
						<select class="form-control" name="zona">
							<%
	                		   	for(int i = 0; i<zonas.size(); i++) {
	                		   		if(zonas.get(i).getId() == s.getZona().getId()) {
	                		 %>
	                		 <option selected value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre() %></option>
	                		 <%
	                		   		} else {
	                		 %>
	                		 <option value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre() %></option>  			
	            	         <%
	                		   		} //else	
	                		   	} // For
	                 	     %>					
						</select>
	               </div>
	               <div class="form-group col-lg-12">
	                   <label for="imagen">URL Imagen</label>
	                   <input type="file" class="form-control" name="imagen">
	                   <img class="img-responsive img-thumbnail" alt="Imagen del sector <%=s.getNombre()%>" src="../uploads/<%=s.getImagen()%>">
	               </div>
	               <!-- Botonera -->
	               <div class="form-group col-lg-12">
	               		<% if(s.getId() != -1) { %>
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
										<i class="fa fa-exclamation-triangle"></i> ELIMINAR:
										<%=s.getNombre().toUpperCase() %></h2>
								</div>
								<div class="modal-body">
									<p>Estas seguro de que desea eliminar el siguiente grado:</p>
									<div class="row">
										<div class="form-group col-md-6">
											<label for="id">ID</label> 
											<input type="text" name="id" class="form-control" value="<%=s.getId()%>" disabled>
										</div>
										<div class="form-group col-md-6">
											<label for="nombre">Nombre</label> 
											<input type="text" name="nombre" class="form-control" value="<%=s.getNombre()%>" disabled>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-12">
											<label for="descripcion">Zona</label>
											<textarea class="form-control" rows="3" name="descripcion" disabled><%=s.getZona().getNombre()%></textarea>
										</div>
									</div>
									<div class="row checkbox">
										<div class="form-group col-md-12">
											<label> <input type="checkbox" id="check_eliminar">S&iacute;,
												estoy seguro. Deseo eliminar el grado seleccionado.
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
									<a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=s.getId()%>" id="boton_eliminar" class="btn btn-danger disabled">Eliminar</a>
									<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
								</div>
							</div>
							<!-- END Modal content-->
						</div>
					<!-- END Ventana Modal -->
				
					<%} else { %>
		                   		<input type="submit" value="Crear" class="btn btn-outline btn-primary">
			                   	<input type="reset" value="Limpiar" class="btn btn-outline btn-danger">
			                <%} %>
		               </div>
		            </div>
	           </form>
          </div>
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>