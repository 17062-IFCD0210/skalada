<%@page import="com.ipartek.formacion.skalada.bean.Mensaje"%>
	<%
		/**
		*	Gestión de los Mensajes para el usuario
		*	Primero busca mensaje en "request",
		*	si no lo encuentra busca en "Session" y luego lo elimina
		*	finalmente muestra el mensaje si no es null
		*/
		Mensaje msg = (Mensaje) request.getAttribute("msg");
		//Si no hay mensaje en la request
		if (msg == null){
			msg = (Mensaje)session.getAttribute("msg");
			session.removeAttribute("msg"); //Para que no lo arrastre
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