Nombre de la Aplicación: skaladaJavi

NOTAS DE INSTALACION:

Para instalar esta aplicación hay que configurar los siguientes parametros:

	En CONSTANTES 
		PORT = "8080"
		SERVER = "http://192.168.1.75" + PORT
		ROOT_APP = "/skaladaJavi/"
		IMG_WEP_PATH = "http://192.168.1.75:8080/uploads/"

	En DATABASEHELPER:
		SERVER = "192.168.1.75";
		DATA_BASE = "eskalada_javi";
		
	En context.xml:
		url="jdbc:mysql://192.168.1.75:3306/eskalada_javi"
		
	En log4j.propierties:
	
	

	Para crear la base de datos existe un script en la carpeta script, llamado "eskalada_javi.sql"

NOTAS DE LA APLICACION:

La aplicacion "skaladaJavi" tiene 2 partes, la pública a la que puede acceder todo el mundo,
y el BackOffice a la que sólo pueden acceder los usuarios registrados y validados.

bla bla bla