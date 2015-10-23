
<%@page import="com.ipartek.formacion.skalada.bean.Sector"%>
<%@page import="com.ipartek.formacion.skalada.bean.Zona"%>
<%@page import="com.ipartek.formacion.skalada.bean.TipoEscalada"%>
<%@page import="com.ipartek.formacion.skalada.bean.Grado"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.bean.Via"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<jsp:include page="../includes/head.jsp"></jsp:include>
<jsp:include page="../includes/nav.jsp"></jsp:include>

<%
	//recojer atributos de la request
	String titulo = request.getAttribute("titulo").toString();
	Via via = (Via)request.getAttribute("via");

%>

    <!-- Detalle via
    ========================================== -->
    <div id="tf-detallevia" class="text-left">
        <div class="overlay">
            <div class="contentvia">
                 <div class="containervia">

	                    <div class="about-text">
		                     <div class="section-title">
		                            <h1 class="letra_naranja"><strong class="letra_blanca"><%=titulo%></strong></h1>
		                            <h5 class="letra_naranja">Zona <strong><%=via.getSector().getZona().getNombre()%></strong></h5>
		                            <hr>
			                      <div class="clearfix">
			                      </div>
	                         </div>
	                        
	                         <ul class="about-list">
	                            <li>
	                                <span class="fa fa-dot-circle-o"></span>
	                                <strong class="letra_naranja">Longitud</strong> - <em class="letra_blanca"><%=via.getLongitud()%> metros</em> </li>
	                            <li>
	                                <span class="fa fa-dot-circle-o"></span>
	                                <strong class="letra_naranja">Dificultad</strong> - <em class="letra_blanca"><%=via.getGrado().getNombre()%></em>
	                                <a class="fa fa-info-circle" data-toggle="modal" data-target="#modal_dificultad"></a>
	                                <!-- Modal dificultad -->
	                                	<div  id="modal_dificultad" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
										  <div class="modal-dialog modal-sm">
										    <div class="modal-content">
										      <p><%=via.getDescripcion()%></p>
										    </div>
										  </div>
										</div>
	                            </li>
	                            <li>
	                                <span class="fa fa-dot-circle-o"></span>
	                                <strong class="letra_naranja">Tipo Escalada</strong> - <em class="letra_blanca"><%=via.getTipoEscalada().getNombre()%></em>
	                                <a class="fa fa-info-circle" data-toggle="modal" data-target="#modal_tipoEscalada"></a>
	                                <!-- Modal Tipo escalada -->
	                                	<div id="modal_tipoEscalada" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
										  <div class="modal-dialog modal-sm">
										    <div class="modal-content">
										      <p>Este tipo de escalada se caracteriza por que en la pared podemos encontrar anclajes fijos colocados estratégicamente para asegurar nuestros pasos. Podemos encontrar anclajes basados en sistemas mecánicos -de expansión- o químicos -resinas-. Por lo general, estas vías al equiparse, se limpian de maleza y de piedras sueltas o susceptibles de romperse, para ganar en la seguridad del escalador deportivo.</p>
										    </div>
										  </div>
										</div>
	                            </li>
	                      		 <li>
	                                <span class="fa fa-dot-circle-o"></span>
	                                <strong class="letra_naranja">Descripción</strong> - <em class="letra_blanca"><%=via.getDescripcion()%></em>
	                            </li>
	                        </ul>
	        
	            		</div>
            </div>
        </div>
   	 </div>
	</div>

<jsp:include page="../includes/foot.jsp"></jsp:include>
    