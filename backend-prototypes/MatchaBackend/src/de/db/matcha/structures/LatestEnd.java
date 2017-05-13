package de.db.matcha.structures;

public class LatestEnd {
	public static final String/*to become an enum*/ MIDNIGHT_OF_STAMPING_DAY = /*i18n*/"Midnight of the validation day";
	public static final String/*to become an enum*/ MINS_60_AFTER_VALIDATION = /*i18n*/"60 minutes after validation (purchase)";
	public static final String/*to become an enum*/ END_OF_SERVICE = /*i18n*/"End of service (e.g. ca. 3 AM of the day following the validation)";
	public String value;
	@Override
	public String toString() {
		return "LatestEnd [value=" + value + "]";
	}
}
