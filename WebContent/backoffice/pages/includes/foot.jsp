<%@page import="com.ipartek.formacion.skalada.Constantes"%>
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
    
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
    	
    	//Habilitar DataTable
        $('#tabla').DataTable({
                responsive: true
        });
    	
    	//Llamada controlador AJAX Sectores
    	console.info('llamada Controlador AJAX Sectores');
    	
    	$('#zona').change(function() {
    		//Recoger id zona seleccionada
    		console.info('zona cambiada');
    		var id_zona = $(this).find("option:selected").val();
    		console.debug('Zona seleccionada: ' + id_zona);
    		
    		//Llamada AJAX al controlador
    		var url =  "<%=Constantes.CONTROLLER_ZONAS_JSON%>";
    		
    		$.ajax( url , {
    			"type": "GET", // usualmente post o get
    			"success": function(result) {
    				rellenarSelectSector(result);
    			},
    			"error": function(result) {
    				console.error("Error ajax", result);
    			},
    			"data": { id_zona: id_zona },
    			"async": true,
    		}); //End: Change zona
    		
    		/**
    		* Limpiar y rellenar de nuevo el select-option con los datos
    		* obtenidos del servicio AJAX para Sectores
    		*/
    		function rellenarSelectSector(result) {
    			console.debug("Vaciar select Sectores");
    			$("#sector").html("");
    			console.debug("Inyectar options");
    			$(result).each( function(i, v) {
    				//console.debug("Index: " + v.id + " value: " + v.nombre);
    				$("#sector").append('<option value="' + v.id + '">' + v.nombre + '</option>');
    			});
    		}
    		
    		
    	}); //End: Document.ready()
    	
    });
    </script>   

</body>

</html>
