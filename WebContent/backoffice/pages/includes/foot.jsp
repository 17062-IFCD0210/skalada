<%@page import="com.ipartek.formacion.skalada.Constantes"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

 </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
<!--     <script src="bower_components/raphael/raphael-min.js"></script> -->
<!--     <script src="bower_components/morrisjs/morris.min.js"></script> -->
<!--     <script src="js/morris-data.js"></script> -->
    
    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
    
   <script>
	    $(document).ready(function() {
	    	
	    	//Habilitar DataTable
	        $('#tabla').DataTable({
	                responsive: true
	        }); // End: DataTable
	    	
	    	//llamada Controlador Ajax Sectores
	    	$('#zona').change(function() {
	    		
	    		//recoger id zona seleccionada
	    		var id_zona = $(this).find("option:selected").val();
	    		console.debug("Zona selecionada = "+id_zona);
	    		
	    		//lamada Ajax al controlador
	    		var url = "<%=Constantes.CONTROLLER_ZONAS_JSON%>";
	    		$.ajax(url, {
	    			"type": "GET", // usualmente post o get
	    			"success": function(result){
	    				rellenarSelectSector(result);
	    			},
	    			"error": function(result) {
		    			console.error("Este ajax", result);
	    			},
	    			"data": {id_zona : id_zona},
	    			"async": true,
	    			});			    		
	    	}); //End: change zona
	    	
	    	/**
	    	*	Limpiar y rellenar de nuevo el Selet con los datos obtenidos 
	    	* 	del servicio Ajax para Sectores
	    	*/
	    	function rellenarSelectSector(result){
	    		console.debug("vaciar select sectores");
	    		$("#sector").html("");
	    		
	    		console.debug("inyectar options");
	    		$(result).each(function(i,v){
	    			//console.debug("id: "+v.id+" value: "+v.nombre);
	    			$("#sector").append("<option value='"+v.id+"'>"+v.nombre+"</option>");
	    		});//End: foreach
	    		
	    	}; //End: rellenarSelectSector
	    	
	    	
	    }); // End: Ready
	    
    </script>

</body>

</html>
