<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.Constantes"%>


<jsp:include page="includes/head.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Registrate</h3>
                    </div>
                    <div class="panel-body">                    
                            
             
   <div class="row">
        <% 
            Mensaje msg = (Mensaje)request.getAttribute("msg");	
			if (msg != null){
				out.print("<div class='alert alert-"+ msg.getTipo() +" alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg.getTexto() +"</strong>");
				out.print("</div>");
			} 
		%>
	</div> <!-- /.row -->   

						
                        <form role="form" id="formNuevoUsuario" name="f1"  method="post" action="<%=Constantes.CONTROLLER_REGISTRO%>" onsubmit="return validar()">
                            <fieldset>
                            	<div class="form-group">
                                    <input class="form-control" placeholder="Nombre" name="nombre" id="nombre" type="text" required autofocus tabindex="1">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" id="email" type="email" required tabindex="2">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Contraseña mínimo 6 caracteres" name="password" id="pw1" type="password" required value="" tabindex="3">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Confirmar Contraseña" name="password2" id="pw2" type="password" required value="" tabindex="4">
                                </div>
                                <input class="btn btn-lg btn-block btn-primary"  type="submit" tabindex="5" value="Registrate"> 
                            </fieldset>
                            
                            <!-- Mensajes de error de validacion -->
							<div id="alertNuevoUsuario" class="alert alert-warning alert-dismissible fade in" role="alert">
							      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
							      <strong><span id="mensajeNuevoUsuario"></span></strong>
							</div>                            
     
                        </form>
                        
<script>
function validar(){
   	p1 = document.getElementById('pw1').value; //document.f1.pw1.value; 
   	p2 = document.getElementById('pw2').value; //document.f1.pw2.value;
   	nombre=document.getElementById('nombre').value; //document.f1.nombre.value;
   	email=document.getElementById('email').value; 
   	console.debug("p1="+p1);
   	console.debug("p2="+p2);
   	console.debug("nombre="+nombre);
   	if ((p1 != p2)){ 
   		$('#mensajeNuevoUsuario').text("No se puede enviar el formulario. Las claves son distintas");
      	$('#alertNuevoUsuario').show();
      	return false;
   	}
   	if ((p1.length<6)){ 
   		$('#mensajeNuevoUsuario').text("No se puede enviar el formulario. El password tiene menos de 6 caracteres");
      	$('#alertNuevoUsuario').show();
      	return false;
   	}
   	if (nombre.length<4){ 
   		$('#mensajeNuevoUsuario').text("No se puede enviar el formulario. El nombre tiene menos de 4 caracteres");
      	$('#alertNuevoUsuario').show();
      	return false;
   	}
   	if(email==''){
   		$('#mensajeNuevoUsuario').text("No se puede enviar el formulario. El email está vacío");
      	$('#alertNuevoUsuario').show();
      	return false;
   	}
   	return true;
   	
} //End: validar

</script>                        
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    


<jsp:include page="includes/foot.jsp"></jsp:include>
