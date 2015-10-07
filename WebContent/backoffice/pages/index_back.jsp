<%@page import="com.ipartek.formacion.skalada.bean.Usuario"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<%
	int user_rol = 0;
	Usuario usuario = (Usuario) session.getAttribute(Constantes.KEY_SESSION_USER);
	if (usuario != null){
		user_rol = usuario.getRol().getId();
	}
%>
<div id="page-wrapper">

	<%
		if(user_rol == Constantes.ROLE_ADMIN_ID){
	%>
	<jsp:include page="dashboard-admin.jsp"></jsp:include>
	<%
		} else {
	%>
	<div class="row">
		<h1>TRABAJANDO EN ELLO</h1>
	</div>
	<%
		}
	%>




	
</div>	<!-- /#page-wrapper -->

<jsp:include page="includes/foot.jsp"></jsp:include>