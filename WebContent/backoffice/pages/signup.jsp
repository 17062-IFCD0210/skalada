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
			    
                        <form role="form" 
							  name="f1" 
							  onsubmit="return validar();"
							  method="post" 
							  action="#">
							  
                            <fieldset>
                            	<div class="form-group">
                                    <input class="form-control" placeholder="Nombre" name="nombre" id="nombre" type="text" required autofocus tabindex="1">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" id="email" type="email" required tabindex="2">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Contraseña minimo 6 caracteres" name="password" id="pw1" type="password" required value="" tabindex="3">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Confirmar Contraseña" name="password2" id="pw2" type="password" required value="" tabindex="4">
                                </div>
                                <input class="btn btn-lg btn-block btn-primary" type="submit" tabindex="5" value="Registrate"> 
                            </fieldset>
                            
                              <script> 
								function validar(){ 
									
									return false;
									
									/*
								   	p1 = document.f1.pw1.value; 
								   	p2 = document.f1.pw2.value; 
								
								   	if (p1 == p2){ 
								      	alert("Las dos claves son iguales...") 
								   	}else{
								      	alert("Las dos claves son distintas...")
								   	} 	
								   	*/
								} 
								</script> 
                        </form>
                    </div>                    
                </div>
            </div>
        </div>
    </div>
    


<jsp:include page="includes/foot.jsp"></jsp:include>
