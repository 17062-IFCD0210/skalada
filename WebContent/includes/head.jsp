<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 

<%@page import="com.ipartek.formacion.skalada.Constantes"%>

<!-- MULTIIDIOMA -->
<%
	Locale locale = (Locale)request.getLocale();

	out.print("Locale: " + locale.getLocale());
%>


<!-- Recuperar language del usuario -->
<c:set var="language" value="eu_ES" scope="session" />
<!-- Setear el locale para el TagLib de FMT -->
<fmt:setLocale value="${language}" />
<!-- Ruta del fichero properties, solo el nombre sin postfijos ni .properties -->
<fmt:setBundle basename="com.ipartek.formacion.skalada.idioma" />

<!DOCTYPE html>
<!-- Indicar el idioma que usamos al HMLT -->
<html lang="${language}"> <!-- <html lang="es"> -->

<head>

	<base href="<%=Constantes.ROOT_APP%>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Modern Business - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>