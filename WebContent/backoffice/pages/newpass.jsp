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
                        <h3 class="panel-title">Nueva Contraseña</h3>
                    </div>
                    <div class="panel-body">
                    <% 
                    
            String email = (String)request.getParameter("email");
            Mensaje msg = (Mensaje)request.getAttribute("msg");	
            
			if (msg != null){
				out.print("<div class='alert alert-"+ msg.getTipo() +" alert-dismissible' role='alert'>");
					out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
						out.print("<span aria-hidden='true'>&times;</span>");
					out.print("</button>");
					out.print("<strong>"+ msg.getTexto() +"</strong>");
				out.print("</div>");
			} 
		%>               <form role="form" name="form1" method="post" action="<%=Constantes.CONTROLLER_FORGOT_PASS %>" onsubmit="return validar();">   
                            <fieldset>
                            	<div class ="form-group">
                            		<div id="alerts" class="row"></div>
                            	</div>
                            	<div class="form-group">
                                    <input type="hidden" name="email" value="<%=email%>">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Contraseña(Mínimo 6 caracteres)" name="password" id="pw1" type="password" required value="" tabindex="1">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Confirmar Contraseña" name="password2" id="pw2" type="password" required value="" tabindex="2">
                                </div>
                                <input type="submit" class="btn btn-lg btn-block btn-primary" value="Cambiar Contraseña">
                                <script> 
								function validar(){ 
									var resul = false;
									var p1 = document.form1.pw1.value; 
								   	var p2 = document.form1.pw2.value;
								   	var val_p1 = document.form1.pw1.value.length;
								   	var val_p2 = document.form1.pw2.value.length;
								   	var mensaje = "";
								   	var tipo = "danger";
	
									if((p1==p2) && (val_p1>=6)) {		
										resul=true;
									} else if(p1 != p2) {
										mensaje = "Contraseñas diferentes";
									} else if(val_p1<6) {
										mensaje = "Contraseña demasiado pequeña";
									}
									$('#alerts').html("<div class='alert alert-"+ tipo +" alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button><strong>"+ mensaje +"</strong></div>")
									return resul;
								}
							</script> 
                            </fieldset>
                          </form>
                    </div>                   
                </div>
            </div>
        </div>
    </div>
    


<jsp:include page="includes/foot.jsp"></jsp:include>