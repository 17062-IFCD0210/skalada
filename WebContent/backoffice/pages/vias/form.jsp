<%@page import="com.ipartek.formacion.skalada.Grado"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//Recoger paramtero "via" de la Clase Via
	Via via = (Via)request.getAttribute("via");

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
	                   <label for="grad">Grado</label>
	                   <select class="form-control" name="grado">
	                   		<% 
	                   		Grado[] grados = Grado.values();
	                   		for(int i=0; i<grados.length; i++) {
	                   			//Comprobamos el grado seleccionado mediante operador ternario
	                   			String selected = (grados[i] == via.getGrado())?"selected":""; 	
	                   		%>
	                   			<option value="<%=grados[i]%>" <%=selected%>><%=grados[i]%></option>		
							<% } %>
	                   </select>
	                   
	                   
<!-- 	                   <select class="form-control" name="grado"> -->
<%-- 	                   		<option hidden="true"><%=via.getGrado()%></option> --%>
<%-- 	                   		<option><%=Grado.FACIL%></option> --%>
<%-- 	                   		<option><%=Grado.DIFICIL%></option> --%>
<%-- 	                   		<option><%=Grado.EXTREMO%></option> --%>
<%-- 	                   		<option><%=Grado.NORMAL%></option> --%>
<!-- 	                   </select> -->
	               </div>
	               <div class="form-group">
	                   <label for="long">Longitud</label>
	                   <input class="form-control" type="number" name="long" value="<%=via.getLongitud()%>" required>
	               </div>
	               <div class="form-group">
	                   <label for="desc">Descripcion</label>
	                   <textarea class="form-control" name="desc" required><%=via.getDescripcion()%></textarea>
	               </div>
	               
	               <!-- Botonera -->
	               <div class="form-group">
	               		<% if(via.getId() != -1) { %>
		               		<input type="submit" value="Modificar" class="btn btn-outline btn-primary">
		                  	<a href="<%=Constantes.CONTROLLER_VIAS%>?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=via.getId()%>">
		                   		<input type="button" value="Eliminar" class="btn btn-outline btn-danger">
		                   	</a>
	               		<%} else { %>
	                   		<input type="submit" value="Crear" class="btn btn-outline btn-primary">
		                   	<input type="reset" value="Limpiar" class="btn btn-outline btn-danger">
		                <%} %>
	               </div>
	           </form>
            </div>
</div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>