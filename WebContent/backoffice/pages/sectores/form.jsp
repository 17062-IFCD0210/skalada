<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="java.awt.Image"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	Sector sector = (Sector)request.getAttribute("sector");
	ArrayList<Zona> zonas = (ArrayList<Zona>)request.getAttribute("zonas");
	Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.KEY_SESSION_USER);
%>

<div id="page-wrapper">

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><%=sector.getNombre().toUpperCase()%></h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
    	
	<!-- Formulario -->
	<!-- @see: http://www.tutorialspoint.com/servlets/servlets-file-uploading.htm -->
	
		<form action="<%=Constantes.CONTROLLER_SECTORES%>"
		      enctype="multipart/form-data" 
		      method="post" 
		      role="form">
			
				<div class="form-group">			
					<!-- Mostramon el input text, pero se submita el hidden -->
					<label for="id">ID</label>
					<input type="hidden" name="id" value="<%=sector.getId()%>">
					<input type="hidden" name="autor" value="<%=usuario.getId()%>">
					<input type="text"  class="form-control" value="<%=sector.getId()%>" disabled >
				</div>
				
				<div class="form-group">
	           		<label for="nombre">Nombre</label>
	           		<input type="text" class="form-control" name="nombre" value="<%=sector.getNombre()%>">
	          	</div>

	          	<div class="form-group">
		            <label for="zona">Zona</label>
		            <select class="form-control" name="zona">
  					<%
  					for (int i = 0 ; i < zonas.size() ; i++){
  					%>
  						
  						    <% if( zonas.get(i).getId() == sector.getZona().getId() ){ %>
  						    	<option selected value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre()%></option>
  						    <%}else{ %>
  								<option value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre()%></option>
  							<%}//end else  						
  					}//end for
					%>
					</select>
				</div>
		      
		      <!-- Imagen -->
		      	<div class="form-group">
		      		<label for="imagen">Imagen</label>
	           		<input type="file" id="imagen" 
	           		       class="form-control" name="imagen"
	           		       onchange="showFileSize();"
	           		       >
	           		
	           		<%
	           			String img_path = Constantes.IMG_DEFAULT_SECTOR;
		      			//imagen subida al servidor	
		      			if ( !img_path.equals( sector.getImagen())){
		      				img_path = Constantes.IMG_WEP_PATH + sector.getImagen();
		      				
		      			//imagen por defecto del proyecto	
		      			}else{
		      				img_path = "../img/" + img_path;
		      			}
	           		%>
	           		
	           		<img src="<%=img_path%>"
	           		 	 alt="Imagen del sector <%=sector.getNombre()%>"
	           			 class="img-responsive img-thumbnail"> 
	           	
		 			<script type='text/javascript'>
						function showFileSize() {
						    var input, file;						   
						    if (!window.FileReader) {
						        bodyAppend("p", "The file API isn't supported on this browser yet.");
						        return;
						    }
						
						    input = document.getElementById('imagen');
						    if ( input.files[0] ){
						    	file = input.files[0];
						    	if ( file.size > <%=Constantes.MAX_FILE_SIZE%> ){
						    		alert( "Demasiado grande la imagen");
						    		document.getElementById('btn_submit').classList.toggle("disabled");			
						    	}else{
						    		document.getElementById('btn_submit').classList.remove("disabled");	
						    	}						    	
						    }   
						}//end showFileSize
					</script>
				</div>
					
					
				<% if(usuario.getRol().getId() == Constantes.ROLE_ID_ADMIN) {%>	
				<div class="form-group">
	           		<label for="validado">Validar</label>
	           		<% if( sector.isValidado() ){ %> 
	           			<input type="checkbox" class="form-control" name="validado" checked 
	           					data-on-text="SI" data-on-color="success"
	           					data-off-text="NO" data-off-color="danger">
	           		<% }else{ %>
	           			<input type="checkbox" class="form-control" name="validado"
								data-on-text="SI" data-on-color="success"
	           					data-off-text="NO" data-off-color="danger">
	           		<% } %>
	          	 </div>  
	          	 <% } %>       	
			
			<!-- Botonera -->
			<div class="form-group">
								
				<% if(sector.getId()!= -1){ %>
						<input type="submit" id="btn_submit" class="btn btn-outline btn-primary" value="Modificar">
  						<!-- Trigger the modal with a button -->
						<button type="button" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#myModal">Eliminar</button>
						
						<!-- Ventana Modal -->
						<div class="modal fade col-md-6 col-md-offset-3" id="myModal" role="dialog">
							<div class="modal-dialog">
						  
						    <!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> ELIMINAR: <%=sector.getNombre().toUpperCase() %></h2>
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
						    			<a href="<%=Constantes.CONTROLLER_SECTORES%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=sector.getId()%>&accion=eliminar" id ="boton_eliminar" class="btn btn-danger btn-xs disabled">Eliminar</a>
						      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						      		</div>
						    	</div> <!-- END Modal content-->
						  	</div>
						</div> <!-- END Ventana Modal -->
			
				
				<% } else { %>
						<input type="submit" id="btn_submit" class="btn btn-outline btn-primary" value="Guardar">
						<button type='reset' class='btn btn-outline btn-warning'>Limpiar</button>
				<% } %>
	
				
			</div>
			
		</form>
   
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            