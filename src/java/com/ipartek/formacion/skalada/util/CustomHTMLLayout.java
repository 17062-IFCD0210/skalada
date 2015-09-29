package com.ipartek.formacion.skalada.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This HTML Log Formatter is a simple replacement for the standard Log4J
 * HTMLLayout formatter and replaces the default timestamp (milliseconds,
 * relative to the start of the log) with a more readable timestamp (an example
 * of the default format is 2008-11-21-18:35:21.472-0800).
 * */

public class CustomHTMLLayout extends org.apache.log4j.HTMLLayout {
	
	static String TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
	
	/*
	 * The timestamp format. The format can be overriden by including the following property in the Log4J configuration file:
	 * log4j.appender.<category>.layout.TimestampFormat
	 * using the same format string as would be specified with SimpleDateFormat.
	 */
	private String timestampFormat = "dd-MMM-yyyy HH:mm:ss"; // Default format. Example: 2008-11-21-18:35:21.472-0800
	private SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
	/**
	 * Setter for timestamp format. Called if
	 * log4j.appender.<category>.layout.TimestampFormat property is specfied
	 */
	public void setTimestampFormat(String format) {
		this.timestampFormat = format;
		this.sdf = new SimpleDateFormat(format); // Use the format specified by the TimestampFormat property
	}

	/** Getter for timestamp format being used. */
	public String getTimestampFormat() {
		return this.timestampFormat;
	}
	
	
	/**
	 * Constructor
	 */
	public CustomHTMLLayout() {
		super();
	}

	
	/** Override HTMLLayout's format() method */
	@Override
	public String format(LoggingEvent event) {
		StringBuffer sbuf = new StringBuffer();
		if (sbuf.capacity() > MAX_CAPACITY) {
			sbuf = new StringBuffer(BUF_SIZE);
		} else {
			sbuf.setLength(0);
		}

		sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

		sbuf.append("<td>");
		sbuf.append(sdf.format(new Date(event.timeStamp)));
		sbuf.append("</td>" + Layout.LINE_SEP);

		sbuf.append("<td title=\"Level\">");
		if (event.getLevel().equals(Level.DEBUG)) {
			sbuf.append("<font color=\"#339933\">");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</font>");
		} else if (event.getLevel().isGreaterOrEqual(Level.WARN)) {
			sbuf.append("<font color=\"#993300\"><strong>");
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
			sbuf.append("</strong></font>");
		} else {
			sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
		}
		sbuf.append("</td>" + Layout.LINE_SEP);

		String escapedLogger = Transform.escapeTags(event.getLoggerName());
		sbuf.append("<td title=\"" + escapedLogger + " category\">");
		sbuf.append(escapedLogger);
		sbuf.append("</td>" + Layout.LINE_SEP);

		LocationInfo locInfo = event.getLocationInformation();
		sbuf.append("<td>");
		sbuf.append(Transform.escapeTags(locInfo.getFileName()));
		sbuf.append(':');
		sbuf.append(locInfo.getLineNumber());
		sbuf.append("</td>" + Layout.LINE_SEP);

		sbuf.append("<td title=\"Message\">");
		sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
		sbuf.append("</td>" + Layout.LINE_SEP);
		sbuf.append("</tr>" + Layout.LINE_SEP);

		if (event.getNDC() != null) {
			sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
			sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
			sbuf.append("</td></tr>" + Layout.LINE_SEP);
		}

		String[] s = event.getThrowableStrRep();
		if (s != null) {
			sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
			appendThrowableAsHTML(s, sbuf);
			sbuf.append("</td></tr>" + Layout.LINE_SEP);
		}

		return sbuf.toString();
	}

	
	void appendThrowableAsHTML(String[] s, StringBuffer sbuf) {
		if (s != null) {
			int len = s.length;
			if (len == 0)
				return;
			sbuf.append(Transform.escapeTags(s[0]));
			sbuf.append(Layout.LINE_SEP);
			for (int i = 1; i < len; i++) {
				sbuf.append(TRACE_PREFIX);
				sbuf.append(Transform.escapeTags(s[i]));
				sbuf.append(Layout.LINE_SEP);
			}
		}
	}

	
	/** Override HTMLLayout's getHeader() method */
	@Override
	public String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<!DOCTYPE HTML>" + Layout.LINE_SEP);
		sbuf.append("<html>" + Layout.LINE_SEP);
		sbuf.append("<head>" + Layout.LINE_SEP);
		sbuf.append("<title>LOG SKALADA</title>" + Layout.LINE_SEP);
		sbuf.append("<style type=\"text/css\">" + Layout.LINE_SEP);
		sbuf.append("<!--" + Layout.LINE_SEP);
		sbuf.append("body, table {font-family: arial,sans-serif; font-size: x-small;}"
				+ Layout.LINE_SEP);
		sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}"
				+ Layout.LINE_SEP);
		sbuf.append("-->" + Layout.LINE_SEP);
		sbuf.append("</style>" + Layout.LINE_SEP);
		sbuf.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"//cdn.datatables.net/1.10.9/css/jquery.dataTables.css\">"
				+ Layout.LINE_SEP);
		sbuf.append("</head>" + Layout.LINE_SEP);
		sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">"
				+ Layout.LINE_SEP);
		sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
		sbuf.append("Log session start time " + new java.util.Date() + "<br>"
				+ Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("<table class=\"tabla-logs\" cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">"
				+ Layout.LINE_SEP);
		sbuf.append("<thead>" + Layout.LINE_SEP);
		sbuf.append("<tr>" + Layout.LINE_SEP);
		sbuf.append("<th>Time</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Level</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Category</th>" + Layout.LINE_SEP);
		sbuf.append("<th>File:Line</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Message</th>" + Layout.LINE_SEP);
		sbuf.append("</tr>" + Layout.LINE_SEP);
		sbuf.append("</thead>" + Layout.LINE_SEP);
		sbuf.append("<tbody>" + Layout.LINE_SEP);
		return sbuf.toString();
	}
	

	/** Override HTMLLayout's getFooter() method */
	@Override
	public String getFooter() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("</tbody>" + Layout.LINE_SEP);
		sbuf.append("</table>" + Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("<script type=\"text/javascript\" charset=\"utf8\" src=\"//code.jquery.com/jquery-1.10.2.min.js\"></script>"
				+ Layout.LINE_SEP);
		sbuf.append("<script type=\"text/javascript\" charset=\"utf8\" src=\"//cdn.datatables.net/1.10.9/js/jquery.dataTables.js\"></script>"
				+ Layout.LINE_SEP);
		sbuf.append("<script>$(document).ready( function () {    $('.tabla-logs').DataTable();} );</script>"
				+ Layout.LINE_SEP);
		sbuf.append("</body></html>" + Layout.LINE_SEP);
		return sbuf.toString();
	}

}