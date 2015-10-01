<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<jsp:include page="includes/head.jsp"></jsp:include>
<jsp:include page="includes/nav.jsp"></jsp:include>
<%@page isErrorPage="true" %>
<style>
	img{
		
		width: 80%;
		height: 500px;
		
	}
	.imagen, .botones{
		margin-left: 20%;
	}
	.letras{
		text-align: center;
		color:red;
		
	}
	
	.lista{
		list-style: none;
	}
	
</style>

<div id="page-wrapper">
    <div class="row">
    
    	<h1 class="letras" ><i class="fa fa-500px fa-10"></i></h1>
    	<h2 class="letras">Perdonen la molestias</h2> 
    	<div class="imagen" >
    		<img src="../img/error500.png">
    	</div>
    	<div class="botones">
	    	<ul>
		    	<li class="lista">		    
			    	<a class="letras" href="">CONTINUAR</a>
		    	</li>
		    	<li  class="lista">
			    	<a class="letras" href="">CONTACTO</a>
		    	</li>
	    	</ul>
    	</div>
    	
   	
    </div> <!-- .row -->
</div> <!-- #page-wrapper" -->



<jsp:include page="includes/foot.jsp"></jsp:include>