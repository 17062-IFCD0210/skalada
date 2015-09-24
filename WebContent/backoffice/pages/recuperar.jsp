<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>


<jsp:include page="includes/head.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Recuperar contraseña</h3>
                    </div>
                    <div class="panel-body">                    

						<% 
				            Mensaje msg = (Mensaje)request.getAttribute("msg");	
							if (msg != null){
								out.print("<div class='alert "+ msg.getTipo() +" alert-dismissible' role='alert'>");
									out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
										out.print("<span aria-hidden='true'>&times;</span>");
									out.print("</button>");
									out.print("<strong>"+ msg.getTexto() +"</strong>");
								out.print("</div>");
							} 
						%>
                        <form action="<%=Constantes.CONTROLLER_REGISTRO%>" method="post" role="form" name="formulario_registro" onsubmit="return validar();">
                           
                            <!-- div para el mensaje alert -->
            				<div id ="alert_placeholder"></div>  
                            
                            <fieldset>                            	
                            	<input type="hidden" name="id" value="0">
                            	<input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                            	  
                                <div class="form-group">
                                    <label for="password">Contraseña (min. 6 caracteres)</label>
                                    <input type="password" class="form-control" name="password" id="pw1" pattern="[a-zA-Z0-9]{6,29}" required tabindex="3">
                                </div>
                                <div class="form-group">
                                	<label for="password2">Confirmar Contraseña</label>
                                    <input type="password" class="form-control" name="password2" id="pw2" required tabindex="4">
                                </div>
                                <input class="btn btn-lg btn-block btn-primary" type="submit" tabindex="5" value="Registrate"> 
                            </fieldset>

							<script> 
								function validar(){ 
								   	var resul = false;
								   	
									p1 = document.formulario_registro.pw1.value;
								   	p2 = document.formulario_registro.pw2.value;
									
									if (p1 == p2){
										resul = true;
									} else {
										document.getElementById('alert_placeholder').innerHTML = '<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert">&times;</a>Las contraseñas no coinciden</div>';
									}
										
									return resul;
								} 
							</script> 

                        </form>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    


<jsp:include page="includes/foot.jsp"></jsp:include>
