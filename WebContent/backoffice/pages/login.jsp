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
                        <h3 class="panel-title">Please Sign In</h3>
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
				                    
                        <form role="form" action="<%=Constantes.CONTROLLER_LOGIN%>" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <input class="btn btn-lg btn-block btn-success" type="submit" value="Login">
                            </fieldset>
                        </form>
                    </div>                   
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading">
                        <h3 class="panel-title">Registro</h3>
                    </div>
                	<div class="panel-body">
                       <fieldset>
                           <a href="pages/signup.jsp" class="btn btn-lg btn-block btn-info" type="button">Registrarse</a>
                       </fieldset>
                    </div>
                </div>
                <a data-toggle="modal" data-target="#myModal">¿Has olvidado tu contraseña?</a>
                <!-- Ventana Modal -->
						<div class="modal fade col-md-6 col-md-offset-3" id="myModal" role="dialog">
							<div class="modal-dialog">
						  
						    <!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h2 class="modal-title text-center text-info">¿Has olvidado tu contraseña?</h2>
						  			</div>
						  			<div class="modal-body">
						    			<div class="row checkbox">
                                        	<div class="form-group col-md-12">
                                        		<form role="form" method="post" action="<%=Constantes.CONTROLLER_FORGOT_PASS %>?accion=<%=Constantes.ACCION_FORGOT_PASS %>" >
							                       <div class="form-group col-md-8">
							                           <input class="form-control" placeholder="Introduce tu E-mail" name="email" id="email" type="email" required tabindex="1">
							                       </div>
							                       <div class="form-group col-md-4">
							                       	   <input type="submit" class="btn btn-md btn-block btn-primary" tabindex="2" value="Solicitar Contraseña">
							                       </div>
							                     </form>
                                           	</div>
                                       	</div>
						  			</div>
						    	</div> <!-- END Modal content-->
						  	</div>
						</div> <!-- END Ventana Modal -->
            </div>
        </div>
    </div>

<jsp:include page="includes/foot.jsp"></jsp:include>