package de.db.matcha.structures;

import java.util.Set;

public class Zone {
	public Set<String> stops;
	public String zoneId;
	@Override
	public String toString() {
		return "Zone [stops=" + stops + ", zoneId=" + zoneId + "]";
	}
}
