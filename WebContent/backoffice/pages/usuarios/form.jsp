<%@page import="com.ipartek.formacion.skalada.bean.Rol"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	Usuario usuario = (Usuario)request.getAttribute("usuario");
	ArrayList<Rol> roles = (ArrayList<Rol>)request.getAttribute("roles");
	String titulo = request.getAttribute("titulo").toString();
%>

<div id="page-wrapper">

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><%=titulo%></h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
    	
	<!-- Formulario -->
	
		<form action="<%=Constantes.CONTROLLER_USUARIOS%>" method="post" role="form">
			
			<div class="row col-lg-12">
				
				<div class="form-group">			
					<!-- Mostramon el input text, pero se submita el hidden -->
					<label for="id">ID</label>
					<input type="hidden" name="id" value="<%=usuario.getId()%>">
					<input type="text"  class="form-control" value="<%=usuario.getId()%>" disabled >
				</div>
				
				<div class="form-group">
	           		<label for="nombre">Nombre</label>
	           		<input type="text" required class="form-control" name="nombre" value="<%=usuario.getNombre()%>">
	          	</div>
	          	
	          	<div class="form-group">
	           		<label for="email">Correo electronico</label>
	           		<input type="email" class="form-control" name="email" value="<%=usuario.getEmail()%>">
	          	</div>
	          	
	          	<div class="form-group">
	           		<label for="password">Contraseña</label>
	           		<input type="password" class="form-control" name="password" value="<%=usuario.getPassword()%>">
	          	</div>
	          	
	          	<div class="form-group">
		            <label for="rol">Rol</label>
		            <select class="form-control" name="rol">
  					<%
	  					for (int i = 0 ; i < roles.size() ; i++){
	  						if( roles.get(i).getNombre().equalsIgnoreCase(usuario.getRol().getNombre()) ){ 
  					%>
  						<option selected value="<%=roles.get(i).getId()%>"><%=roles.get(i).getNombre()%></option>
  					<%
  						} else { 
  					%>
  						<option value="<%=roles.get(i).getId()%>"><%=roles.get(i).getNombre()%></option>
  					<%
  							}//end else  						
  						}//end for
					%>
					</select>
					
					<div class="form-group">
						<label for="validado">Validado</label>
					 	<%
		           			if(usuario.getValidado() == Constantes.USER_VALIDATE){
		           		%>
							<input type="checkbox" checked name="validado" value="<%=Constantes.USER_VALIDATE%>"> 
					    <%
		           			} else {
		          		%>
		          			<input type="checkbox" name="validado" value="<%=Constantes.USER_VALIDATE%>">
		          		<%
		           			}
		          		%>
					    
					</div>	           		

	        </div>
	        

			
			<!-- Botonera -->
			<div class="form-group">
								
				<% if(usuario.getId()!= -1){ %>
						<input type="submit" class="btn btn-outline btn-primary" value="Modificar / Guardar">
  						<!-- Trigger the modal with a button -->
						<button type="button" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#myModal">Eliminar</button>
						
						<!-- Ventana Modal -->
						<div class="modal fade col-md-6 col-md-offset-3" id="myModal" role="dialog">
							<div class="modal-dialog">
						  
						    <!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> ELIMINAR: <%=usuario.getNombre().toUpperCase() %></h2>
						  			</div>
						  			<div class="modal-body">
						    			<div class="row checkbox">
                                        	<div class="form-group col-md-12">
                                        		<label>
                                            		<input type="checkbox" id="check_eliminar">Marca la casilla para eliminar
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
						    			<a href="<%=Constantes.CONTROLLER_USUARIOS%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=usuario.getId()%>&accion=eliminar" id ="boton_eliminar" class="btn btn-danger btn-xs disabled">Eliminar</a>
						      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						      		</div>
						    	</div> <!-- END Modal content-->
						  	</div>
						</div> <!-- END Ventana Modal -->
			
				
				<% } else { %>
						<input type="submit" class="btn btn-outline btn-primary" value="Crear / Guardar">
						<button type='reset' class='btn btn-outline btn-warning'>Limpiar</button>
				<% } %>
	
				
			</div>
			
		</form>
   
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            