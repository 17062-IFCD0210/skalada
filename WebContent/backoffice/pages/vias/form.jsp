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
                    <h1 class="page-header">Edici&oacute;n Via</h1>
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
	                   <input class="form-control" name="nombre" value="<%=via.getNombre()%>">
	               </div>
	               <div class="form-group">
	                   <label for="grad">Grado</label>
	                   <input class="form-control" name="grado" value="<%=via.getGrado()%>">
	               </div>
	               <div class="form-group">
	                   <label for="long">Longitud</label>
	                   <input class="form-control" name="long" value="<%=via.getLongitud()%>">
	               </div>
	               <div class="form-group">
	                   <label for="desc">Descripcion</label>
	                   <input class="form-control" name="desc" value="<%=via.getDescripcion()%>">
	               </div>
	               
	               <!-- Botonera -->
	               <div class="form-group">
	                   <input type="submit" value="Modificar" class="btn btn-outline btn-primary">
	                   <input type="button" value="Eliminar" class="btn btn-outline btn-danger">
	               </div>
	           			
	           </form>
            </div>
            
        </div>
        
<jsp:include page="../includes/foot.jsp"></jsp:include>