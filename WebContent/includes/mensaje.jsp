<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
<%
	/**
	*	Gestion de los Mensajes para el Usuario
	*	Primero busca mensaje en "Request"
	*	Si no lo encuentra busca en "Session" y lo elimina
	*	muestra en pantalla el mensaje
	*/
	Mensaje msg = (Mensaje) request.getAttribute("msg");
	if(msg == null) {
		msg = (Mensaje) session.getAttribute("msg");
		if(msg != null) {
			session.removeAttribute("msg");
		}
	}
	if (msg != null) {
		out.print("<div class='alert alert-" + msg.getTipo()
				+ " alert-dismissible' role='alert'>");
		out.print("<button type='button' class='close' data-dismiss='alert' aria-label='Close'>");
		out.print("<span aria-hidden='true'>&times;</span>");
		out.print("</button>");
		out.print("<strong>" + msg.getTexto() + "</strong>");
		out.print("</div>");
	}
%>