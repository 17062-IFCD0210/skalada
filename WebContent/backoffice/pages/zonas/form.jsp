<%@page pageEncoding = "utf-8" %>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//Recoger atributos "grado" de la Clase Grado
	Grado g = (Grado)request.getAttribute("grado");

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
	           <form role="form" action ="<%=Constantes.CONTROLLER_GRADOS%>" method="post">
		           <div class="form-group">
		           	<!-- Mostramos el input text, pero se submita el hidden -->
	                   <label for="id">ID</label>
	                   <input type="hidden" name="id" value="<%=g.getId()%>">
	                   <input type="text" class="form-control" value="<%=g.getId()%>" disabled>
	               </div>
	               <div class="form-group">
	                   <label for="nombre">Nombre</label>
	                   <input class="form-control" name="nombre" value="<%=g.getNombre()%>" required>
	               </div>
	              <div class="form-group">
	                   <label for="desc">Descripcion</label>
	                   <textarea class="form-control" name="desc" required><%=g.getDescripcion()%></textarea>
	               </div>
	               <!-- Botonera -->
	               <div class="form-group">
	               		<% if(g.getId() != -1) { %>
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
										<%=g.getNombre().toUpperCase() %></h2>
								</div>
								<div class="modal-body">
									<p>Estas seguro de que desea eliminar el siguiente grado:</p>
									<div class="row">
										<div class="form-group col-md-6">
											<label for="id">ID</label> 
											<input type="text" name="id" class="form-control" value="<%=g.getId()%>" disabled>
										</div>
										<div class="form-group col-md-6">
											<label for="nombre">Nombre</label> 
											<input type="text" name="nombre" class="form-control" value="<%=g.getNombre()%>" disabled>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-12">
											<label for="descripcion">Descripcion</label>
											<textarea class="form-control" rows="3" name="descripcion" disabled><%=g.getDescripcion()%></textarea>
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
									<a href="<%=Constantes.CONTROLLER_GRADOS%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=g.getId()%>" id="boton_eliminar" class="btn btn-danger disabled">Eliminar</a>
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