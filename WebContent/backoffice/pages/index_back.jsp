<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<%@page errorPage="error.jsp"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<%

Usuario usuario=(Usuario)session.getAttribute(Constantes.KEY_SESSION_USER);

int rol = usuario.getRol().getId(); //1 es admin 2 usuario normal

if (rol== Constantes.ROLE_ADMIN_ID){ %>
	<jsp:include page="index_back-admin.jsp"></jsp:include>
<%}else{ %>
	<jsp:include page="index_back-user.jsp"></jsp:include>
<%} 
%>

   

