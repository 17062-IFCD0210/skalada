<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<%
Usuario usuario=(Usuario)session.getAttribute(Constantes.KEY_SESSION_USER);
int rol = usuario.getRol().getId(); //1 es admin 2 usuario normal
if (rol== Constantes.ROLE_ID_ADMIN ){ %>
	<jsp:include page="indexbackadmin.jsp"></jsp:include>
<%}else{ %>
	<jsp:include page="indexbackuser.jsp"></jsp:include>
<%} 
%>

<!-- Recoger parametros para los usuarios y sectores -->



