 <%@page import="com.ipartek.formacion.skalada.Constantes"%>
</div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
    
    <!-- TinyMce Plugin JavaScript -->
    <script src="js/tinymce/tinymce.min.js"></script>

    <!-- Morris Charts JavaScript -->
<!--     <script src="bower_components/raphael/raphael-min.js"></script> -->
<!--     <script src="bower_components/morrisjs/morris.min.js"></script> -->
<!--     <script src="js/morris-data.js"></script> -->
    
    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
    
    <!-- Enganche para DataTable -->
    <script>
	    $(document).ready(function() {
	    	
	    	
	    	//HAbilitar TinyMCE
	    	tinymce.init({
	    	    selector: "textarea",
	    	    theme: "modern",
	    	    min_height: 200,
	    	    plugins: [
	    	        "advlist autolink lists link image charmap print preview anchor",
	    	        "searchreplace visualblocks code fullscreen",
	    	        "insertdatetime media table contextmenu paste"
	    	    ],
	    	    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
	    	});  
	       	
	    	//Habilitar DataTable
	    	$('#tabla').DataTable({
	    		aLengthMenu: [
	    		              [10, 25, 50, -1],
	    		              [10, 25, 50,"All"]
	    		          ],
	    		iDisplayLength: 25,
	            responsive: true
	        });
	    	
	    	//Llamada Controlador Ajax Sectores	 	    	
	    	$( "#zona" ).change(function() {	    		
	    		//recoger id zona seleccionada
	    		var id_zona = $(this).find("option:selected").val();
	    		console.debug ("Zona seleccionada = " + id_zona);
	    		
	    		//llamada Ajax al controlador
	    		//Url donde se encuentra el servicio Ajax
	    		var url = "<%=Constantes.CONTROLLER_ZONA_JSON %>";
	    		
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
	    		});	//END: llamada AJAX
	    			     		
	    	});	//END: change #zona
	    	
	    	/**
	    	 * Limpiar y rellenar el select-options con los datos
	    	 * obtenidos del servicio Ajax para los sectores
	    	 */
	    	function rellenarSelectSector(result) {
	    		console.debug("vaciar select sectores");
	    		$("#sector").html("");
	    		console.debug("injectar options");
	    		
	    		$(result).each( function ( index , value ) {
	    			$("#sector").append('<option value="' + value.id + '">' + value.nombre + '</option>');
	    		});	//END: foreach
	    		
	    	};	//END: rellenarSelectSector
	    	
	    	
	    	
	    	$("#img_select").change(function() {
	    		var imagen = $(this).find("option:selected").val();
	    		$("#img_mostrar").attr("src", "../uploads/" + imagen);
	    	});
	    	
	    	
	    }); //END: document ready	    
    </script>

</body>

</html>
