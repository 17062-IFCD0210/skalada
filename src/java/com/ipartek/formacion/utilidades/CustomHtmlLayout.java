package com.ipartek.formacion.utilidades;

import org.apache.log4j.Layout;

/**
 * This HTML Log Formatter is a simple replacement for the standard Log4J
 * HTMLLayout formatter and replaces the default timestamp (milliseconds,
 * relative to the start of the log) with a more readable timestamp (an example
 * of the default format is 2008-11-21-18:35:21.472-0800).
 * */

public class CustomHtmlLayout extends org.apache.log4j.HTMLLayout{

	@Override
	public String getHeader() {
	
		//return super.getHeader();
		StringBuffer sbuf = new StringBuffer();
		//TODO cargar estilos y demas de datatable
	    sbuf.append("<!DOCTYPE HTML>"  + Layout.LINE_SEP);
	    sbuf.append("<html>" + Layout.LINE_SEP);
	    sbuf.append("<head>" + Layout.LINE_SEP);
	    sbuf.append("<title>CAMBIADO</title>" + Layout.LINE_SEP);
	    sbuf.append("<style type=\"text/css\">"  + Layout.LINE_SEP);
	    sbuf.append("<!--"  + Layout.LINE_SEP);
	    sbuf.append("body, table {font-family: arial,sans-serif; font-size: x-small;}" + Layout.LINE_SEP);
	    sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
	    sbuf.append("-->" + Layout.LINE_SEP);
	    sbuf.append("</style>" + Layout.LINE_SEP);
	    sbuf.append("</head>" + Layout.LINE_SEP);
	    sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" + Layout.LINE_SEP);
	    sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
	    sbuf.append("Log session start time " + new java.util.Date() + "<br>" + Layout.LINE_SEP);
	    sbuf.append("<br>" + Layout.LINE_SEP);
	    sbuf.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" + Layout.LINE_SEP);
	    sbuf.append("<tr>" + Layout.LINE_SEP);
	    sbuf.append("<th>Time</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Thread</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Level</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>Category</th>" + Layout.LINE_SEP);
	    sbuf.append("<th>File:Line</th>" + Layout.LINE_SEP);	    
	    sbuf.append("<th>Message</th>" + Layout.LINE_SEP);
	    sbuf.append("</tr>" + Layout.LINE_SEP);
	    return sbuf.toString();
	}
	
	
	@Override
	public String getFooter() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("</table>" + Layout.LINE_SEP);
	    return sbuf.toString();
	}
}
