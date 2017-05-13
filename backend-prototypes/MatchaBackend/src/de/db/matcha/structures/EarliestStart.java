package de.db.matcha.structures;

import java.text.DateFormat;

public class EarliestStart {
	public static final String/*to become an enum*/ AFTER_MORNING_RUSH_HOUR = /*i18n*/"9:00 AM (in the morning, after rush hour)";
	public static final String/*to become an enum*/ AT_DAY_START = /*i18n*/"0:01 AM (in the morning)";
	public DateFormat format =  DateFormat.getTimeInstance(DateFormat.SHORT); //to be used
	public String value;
	@Override
	public String toString() {
		return "EarliestStart [format=" + format + ", value=" + value + "]";
	}
}
