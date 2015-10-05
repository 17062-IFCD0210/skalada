package com.ipartek.formacion.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This HTML Log Formatter is a simple replacement for the standard Log4J
 * HTMLLayout formatter and replaces the default timestamp (milliseconds,
 * relative to the start of the log) with a more readable timestamp (an example
 * of the default format is 2008-11-21-18:35:21.472-0800).
 * 
 * @author Javi
 * */

public class HtmlLayoutlLog4j extends org.apache.log4j.HTMLLayout {

	// RegEx pattern looks for <tr> <td> nnn...nnn </td> (all whitespace
	// ignored)

	private static final String RXTIMESTAMP = "\\s*<\\s*tr\\s*>\\s*<\\s*td\\s*>\\s*(\\d*)\\s*<\\s*/td\\s*>";

	/*
	 * The timestamp format. The format can be overriden by including the
	 * following property in the Log4J configuration file:
	 * 
	 * log4j.appender.<category>.layout.TimestampFormat
	 * 
	 * using the same format string as would be specified with SimpleDateFormat.
	 */

	private String timestampFormat = "yyyy-MM-dd-HH:mm:ss.SZ"; // Default
	// format.
	// Example:
	// 2008-11-21-18:35:21.472-0800

	private SimpleDateFormat sdf = new SimpleDateFormat(this.timestampFormat);

	public HtmlLayoutlLog4j() {
		super();
	}

	/** Override HTMLLayout's format() method */

	@Override
	public String format(LoggingEvent event) {
		String record = super.format(event); // Get the log record in the
		// default HTMLLayout format.

		Pattern pattern = Pattern.compile(RXTIMESTAMP); // RegEx to find the
		// default timestamp
		Matcher matcher = pattern.matcher(record);

		if (!matcher.find()) // If default timestamp cannot be found,
		{
			return record; // Just return the unmodified log record.
		}

		StringBuffer buffer = new StringBuffer(record);

		buffer.replace(matcher.start(1), // Replace the default timestamp with
				// one formatted as desired.
				matcher.end(1), this.sdf.format(new Date(event.timeStamp)));

		return buffer.toString(); // Return the log record with the desired
		// timestamp format.
	}

	/**
	 * Setter for timestamp format. Called if
	 * log4j.appender.<category>.layout.TimestampFormat property is specfied
	 */

	public void setTimestampFormat(String format) {
		this.timestampFormat = format;
		this.sdf = new SimpleDateFormat(format); // Use the format specified by
		// the TimestampFormat
		// property
	}

	/** Getter for timestamp format being used. */

	public String getTimestampFormat() {
		return this.timestampFormat;
	}

	@Override
	public String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<!DOCTYPE HTML>" + Layout.LINE_SEP);
		sbuf.append("<html>" + Layout.LINE_SEP);
		sbuf.append("<head>" + Layout.LINE_SEP);
		sbuf.append("<title>CAMBIADO</title>" + Layout.LINE_SEP);
		sbuf.append("<style type=\"text/css\">" + Layout.LINE_SEP);
		sbuf.append("<!--" + Layout.LINE_SEP);
		sbuf.append("body, table {font-family: arial,sans-serif; font-size: x-small;}"
				+ Layout.LINE_SEP);
		sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}"
				+ Layout.LINE_SEP);
		sbuf.append("-->" + Layout.LINE_SEP);
		sbuf.append("</style>" + Layout.LINE_SEP);
		sbuf.append("</head>" + Layout.LINE_SEP);
		sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">"
				+ Layout.LINE_SEP);
		sbuf.append("<hr size=\"1\" noshade>" + Layout.LINE_SEP);
		sbuf.append("Log session start time " + new java.util.Date() + "<br>"
				+ Layout.LINE_SEP);
		sbuf.append("<br>" + Layout.LINE_SEP);
		sbuf.append("<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">"
				+ Layout.LINE_SEP);
		sbuf.append("<tr>" + Layout.LINE_SEP);
		sbuf.append("<th>Time</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Thread</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Level</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Category</th>" + Layout.LINE_SEP);
		sbuf.append("<th>File:Line</th>" + Layout.LINE_SEP);
		sbuf.append("<th>Message</th>" + Layout.LINE_SEP);
		sbuf.append("</tr>" + Layout.LINE_SEP);
		return sbuf.toString();
		// return super.getHeader();
	}

}