<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>

<div id="page-wrapper">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Logs</h1>
		</div><!-- /.col-lg-12 -->
	</div> <!-- /.row -->
	<div class="row">
	
		<div class="embed-responsive embed-responsive-4by3">
	  		<embed class="embed-responsive-item" src="<%=Constantes.VIEW_BACK_LOGS%>"></embed>
		</div>	
	
		
	</div><!-- /.row -->
</div><!-- /#page-wrapper -->


<jsp:include page="includes/foot.jsp"></jsp:include>


