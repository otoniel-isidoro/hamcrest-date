
package org.exparity.hamcrest.date;

import static java.util.Calendar.getInstance;
import static org.exparity.hamcrest.date.DateFormatter.*;
import java.util.Calendar;
import java.util.Date;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * A matcher that tests that the examined date is before the reference date
 * 
 * @author Stewart Bissett
 */
public class IsBefore extends TypeSafeDiagnosingMatcher<Date> {

	private final Date expected;

	public IsBefore(final Date expected) {
		this.expected = expected;
	}

	@Override
	protected boolean matchesSafely(final Date actual, final Description mismatchDesc) {
		if (expected.compareTo(actual) <= 0) {
			mismatchDesc.appendText("date is ").appendValue(formatDateWithMillis(actual));
			return false;
		} else {
			return true;
		}
	}

	public void describeTo(final Description description) {
		description.appendText("the date is before ").appendValue(formatDateWithMillis(expected));
	}

	/**
	 * Creates a matcher that matches when the examined date is before the reference date
	 * <p/>
	 * For example:
	 * 
	 * <pre>
	 * assertThat(myDate, before(new Date()))
	 * </pre>
	 * 
	 * @param date
	 *            the reference date against which the examined date is checked
	 */
	@Factory
	public static Matcher<Date> before(final Date date) {
		return new IsBefore(date);
	}

	/**
	 * Creates a matcher that matches when the examined date is before the reference date
	 * <p/>
	 * For example:
	 * 
	 * <pre>
	 * assertThat(myDate, before(Moments.today()));
	 * </pre>
	 * 
	 * @param date
	 *            the reference date against which the examined date is checked
	 */
	@Factory
	public static Matcher<Date> before(final DayMonthYear date) {
		return before(date.getYear(), date.getMonth(), date.getDay());
	}

	/**
	 * Creates a matcher that matches when the examined date is before the start of the reference date
	 * <p/>
	 * For example:
	 * 
	 * <pre>
	 * assertThat(myDate, before(2012, Months.MAY, 12));
	 * </pre>
	 * 
	 * @param year
	 *            the year against which the examined date is checked
	 * @param month
	 *            the month against which the examined date is checked
	 * @param day
	 *            the day of the month against which the examined date is checked
	 */
	@Factory
	public static Matcher<Date> before(final int year, final Months month, final int day) {
		return before(year, month, day, 00, 00, 00);
	}

	/**
	 * Creates a matcher that matches when the examined date is before the start of the reference date and time
	 * <p/>
	 * For example:
	 * 
	 * <pre>
	 * assertThat(myDate, before(2012, Months.MAY, 12, 23, 00, 01));
	 * </pre>
	 * 
	 * @param year
	 *            the year against which the examined date is checked
	 * @param month
	 *            the month against which the examined date is checked
	 * @param day
	 *            the day of the month against which the examined date is checked
	 * @param hour
	 *            the hour of the day against which the examined date is checked
	 * @param minute
	 *            the minute of the hour against which the examined date is checked
	 * @param second
	 *            the second of the minute against which the examined date is checked
	 */
	@Factory
	public static Matcher<Date> before(final int year, final Months month, final int date, final int hour, final int minute, final int second) {
		Calendar calendar = getInstance();
		calendar.set(year, month.calendarConstant(), date, hour, minute, second);
		calendar.set(Calendar.MILLISECOND, 0);
		return new IsBefore(calendar.getTime());
	}

}