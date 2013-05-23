/*
 * Copyright (c) Modular IT Limited.
 */

package uk.co.it.modular.hamcrest.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formatter
 * 
 * @author Stewart Bissett
 */
abstract class DateFormatter {

	private static final String DATE_PATTERN_WITH_MILLIS = "dd MMM yyyy HH:mm:ss SSS'ms' Z";

	/**
	 * Format the date into a string showing milliseconds
	 */
	static String formatDateWithMillis(final Date date) {
		return new SimpleDateFormat(DATE_PATTERN_WITH_MILLIS).format(date);
	}

}