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
                                <div class="form-group">
                                	<input class="btn btn-lg btn-block btn-success" type="submit" value="login">
                                </div>
                            </fieldset>
                        </form>
                    </div>
				</div>
                <br>
                <br>
                <a href="<%=Constantes.VIEW_BACK_SIGNUP%>" class="btn btn-lg btn-block btn-default">Registro nuevo usuario</a>
                <br>
                <a href="<%=Constantes.VIEW_BACK_RECORDAR_PASSWORD%>" class="">¿Has olvidado tu contraseña?</a>
            </div>
        </div>
    </div>

<jsp:include page="includes/foot.jsp"></jsp:include>

