<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>


<jsp:include page="includes/head.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
            
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
            
            
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Iniciar Sesión</h3>
                    </div>
                    <div class="panel-body">   
                    
                        <form role="form" action="<%=Constantes.CONTROLLER_LOGIN%>" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Username or email" name="user" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <input class="btn btn-lg btn-block btn-success" type="submit" value="login">
                            </fieldset>
                        </form>
                    </div>                    
                </div>
                
                <br><br>
                <a href="<%=Constantes.VIEW_BACK_SIGNUP %>" class="btn btn-lg btn-block btn-default">Registro nuevo usuario</a>
                <br>
                <p data-toggle="modal" data-target="#myModal">¿Has olvidado la contraseña?</p>
                
                <!-- Ventana Modal -->
				<div class="modal fade col-md-6 col-md-offset-3" id="myModal" role="dialog">
					<div class="modal-dialog">				  
				    	<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h2 class="modal-title text-center">Recuperar Contraseña</h2>
				  			</div>
				  			<form action="<%=Constantes.CONTROLLER_FORGOT_PASS%>" method="get">
				  				<div class="modal-body col-md-offset-1 col-md-10">
			    					<p>Introduce tu correo electronico para recuperar la contraseña</p>
                                   	<div class="form-group">
					           			<label for="email">Correo electronico</label>
					           			<input type="email" class="form-control" name="email">
					          		</div>
					     		                              
				  				</div>
				  				<div class="modal-footer">						    			
				    				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				    				<input type="submit" class="btn btn-info" value="Recuperar">
				      			</div>
				      		</form> 
				    	</div> <!-- END Modal content-->
				  	</div>
				</div> <!-- END Ventana Modal -->   
                
                
            </div>
        </div>
    </div>

<jsp:include page="includes/foot.jsp"></jsp:include>

