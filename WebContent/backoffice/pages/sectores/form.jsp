<%@page import="java.io.File"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//recoger atributos (Objeto Sector)"sector" y (String)"titulo"
	Sector sector = (Sector)request.getAttribute("sector");
	ArrayList<Zona> zonas = (ArrayList<Zona>)request.getAttribute("zonas");
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
	
		<form action="<%=Constantes.CONTROLLER_SECTORES%>" 
			  enctype="multipart/form-data"
			  method="post" role="form">
			
			<div class="form-group">			
					<!-- Mostramon el input text, pero se submita el hidden -->
					<label for="id">ID</label>
					<input type="hidden" name="id" value="<%=sector.getId()%>">
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
	  						if( zonas.get(i).getId() == sector.getZona().getId() ){ 
  					%>
				    	<option selected value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre()%></option>
				    <%		
				    		}else{ 
				    %>
						<option value="<%=zonas.get(i).getId()%>"><%=zonas.get(i).getNombre()%></option>
					<%	
							} 						
  						}
					%>
					</select>
		        </div>
		        
		        <div class="form-group">
	           		<label for="img">Imagen existente</label>
	           		<select class="form-control" name="img" id="img_select">
	           		<%
	           			File f = new File(Constantes.IMG_UPLOAD_FOLDER);
           				File[] ficheros = f.listFiles();
           				for (int i = 0 ; i<ficheros.length; i++){
           				  	if( ficheros[i].getName().equalsIgnoreCase(sector.getImagen())){
           			%>
           				<option selected value="<%=ficheros[i].getName()%>"><%=ficheros[i].getName()%></option>
           			<%	
           				  	} else {
           			%>
    					<option value="<%=ficheros[i].getName()%>"><%=ficheros[i].getName()%></option>
    				<%
           				 	} 						
  						}	           		
	           		%>	
	           		</select>           		
	          		<%
	           			String img_path = Constantes.IMG_DEFAULT_SECTOR;
		      			if ( !img_path.equals( sector.getImagen())){
		      				img_path = Constantes.IMG_WEP_PATH + sector.getImagen();
		      			}else{
		      				img_path = "../img/" + img_path;
		      			}	
	           		%>
	          		<img src="<%=Constantes.IMG_WEP_PATH%><%=sector.getImagen() %>"
	           			 alt="Imagen del sector <%=sector.getNombre()%>"
	           			 class="img-responsive img-thumbnail"
	           			 id="img_mostrar">
	           	</div>
	           	<div class="form-group">	
	           		<label for="imagen">Nueva imagen</label>
	           		<input type="file" class="form-control" name="imagen">	  
	           	</div>
	        
	        

			
			<!-- Botonera -->
			<div class="form-group">
								
				<% if(sector.getId()!= -1){ %>
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
						<input type="submit" class="btn btn-outline btn-primary" value="Crear / Guardar">
						<button type='reset' class='btn btn-outline btn-warning'>Limpiar</button>
				<% } %>
	
				
			</div>
			
		</form>
   
    </div>
    
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>            